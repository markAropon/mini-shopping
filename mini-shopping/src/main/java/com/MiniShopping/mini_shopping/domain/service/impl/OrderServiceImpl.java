package com.MiniShopping.mini_shopping.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MiniShopping.mini_shopping.domain.dao.OrderDao;
import com.MiniShopping.mini_shopping.domain.dao.ProductDao;
import com.MiniShopping.mini_shopping.domain.dao.StatusDao;
import com.MiniShopping.mini_shopping.domain.dao.UserDao;
import com.MiniShopping.mini_shopping.domain.dto.CreateOrderDTO;
import com.MiniShopping.mini_shopping.domain.dto.OrderDTO;
import com.MiniShopping.mini_shopping.domain.dto.OrderItemDTO;
import com.MiniShopping.mini_shopping.domain.entity.Order;
import com.MiniShopping.mini_shopping.domain.entity.OrderItem;
import com.MiniShopping.mini_shopping.domain.entity.Product;
import com.MiniShopping.mini_shopping.domain.entity.Status;
import com.MiniShopping.mini_shopping.domain.entity.Users;
import com.MiniShopping.mini_shopping.domain.service.OrderService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final ProductDao productDao;
    private final StatusDao statusDao;

    @Override
    @Transactional
    public OrderDTO createOrder(CreateOrderDTO createOrderDTO, String username) {
        Users user = userDao.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
        
        Order order = new Order();
        order.setUser(user);
        
        Status pendingStatus = statusDao.findByStatusName("PENDING");
        if (pendingStatus == null) {
            throw new RuntimeException("PENDING status not found in database");
        }
        
        order.setStatus(pendingStatus);
        
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;
        
        for (OrderItemDTO itemDTO : createOrderDTO.getItems()) {
            Product product = productDao.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + itemDTO.getProductId()));
            
            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }
            
            // Update product stock
            product.setStock(product.getStock() - itemDTO.getQuantity());
            productDao.save(product);
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(product.getPrice());
            
            orderItems.add(orderItem);
            totalAmount += (product.getPrice() * itemDTO.getQuantity());
        }
        
        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);
        
        Order savedOrder = orderDao.save(order);
        return mapToDTO(savedOrder);
    }

    @Override
    public List<OrderDTO> getMyOrders(String username) {
        Users user = userDao.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
        
        List<Order> orders = orderDao.findByUser(user);
        return orders.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderDao.findAll();
        return orders.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long id, String statusName) {
        Order order = orderDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        
        Status status = statusDao.findByStatusName(statusName);
        if (status == null) {
            throw new RuntimeException("Status not found: " + statusName);
        }
        
        order.setStatus(status);
        Order updatedOrder = orderDao.save(order);
        
        return mapToDTO(updatedOrder);
    }
    
    private OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setUsername(order.getUser().getUsername());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus().getStatusName());
        
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> {
                    OrderItemDTO itemDTO = new OrderItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setProductId(item.getProduct().getId());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setPrice(item.getPrice());
                    return itemDTO;
                })
                .collect(Collectors.toList());
        
        orderDTO.setItems(itemDTOs);
        return orderDTO;
    }
}
