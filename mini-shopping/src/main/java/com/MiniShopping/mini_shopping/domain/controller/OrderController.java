package com.MiniShopping.mini_shopping.domain.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.MiniShopping.mini_shopping.common.ApiResponse;
import com.MiniShopping.mini_shopping.common.DefaultResponse;
import com.MiniShopping.mini_shopping.domain.dto.CreateOrderDTO;
import com.MiniShopping.mini_shopping.domain.dto.OrderDTO;
import com.MiniShopping.mini_shopping.domain.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Customer Orders", description = " Order endpoints for creating and viewing customer orders - requires authentication")
public class OrderController {

    private final OrderService orderService;
    
    @Operation(
        summary = "Create a new order", 
        description = "Create a new order with the provided items (CUSTOMER)",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    public ApiResponse<OrderDTO> createOrder(@Valid @RequestBody CreateOrderDTO orderDTO, Principal principal) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO, principal.getName());
        return DefaultResponse.displayCreatedObject(createdOrder);
    }

    @Operation(
        summary = "Get my orders", 
        description = "View all orders placed by the authenticated customer (Customer/Admin)",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    public ApiResponse<List<OrderDTO>> getMyOrders(Principal principal) {
        List<OrderDTO> orders = orderService.getMyOrders(principal.getName());
        return DefaultResponse.displayList(orders);
    }
}
