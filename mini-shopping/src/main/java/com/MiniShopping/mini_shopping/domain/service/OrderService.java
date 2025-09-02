package com.MiniShopping.mini_shopping.domain.service;

import java.util.List;

import com.MiniShopping.mini_shopping.domain.dto.CreateOrderDTO;
import com.MiniShopping.mini_shopping.domain.dto.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(CreateOrderDTO orderDTO, String username);
    List<OrderDTO> getMyOrders(String username);
    List<OrderDTO> getAllOrders();
    OrderDTO updateOrderStatus(Long id, String status);
}
