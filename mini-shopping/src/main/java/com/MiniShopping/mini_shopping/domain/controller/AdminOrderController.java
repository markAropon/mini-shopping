package com.MiniShopping.mini_shopping.domain.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotBlank;

import com.MiniShopping.mini_shopping.common.ApiResponse;
import com.MiniShopping.mini_shopping.common.DefaultResponse;
import com.MiniShopping.mini_shopping.domain.dto.OrderDTO;
import com.MiniShopping.mini_shopping.domain.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@Tag(name = "Admin Orders", description = "Order management endpoints for administrators - requires ADMIN role")
public class AdminOrderController {

    private final OrderService orderService;
    
    @Operation(
        summary = "Get all orders", 
        description = "View all orders in the system (ADMIN only)",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping
    public ApiResponse<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return DefaultResponse.displayFoundObject(orders);
    }
    
    @Operation(
        summary = "Update order status", 
        description = "Update the status of an order (ADMIN only)",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/{id}/status")
    public ApiResponse<OrderDTO> updateOrderStatus(
            @PathVariable Long id, 
            @RequestParam @NotBlank(message = "Status is required") String status) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(id, status);
        return DefaultResponse.displayUpdatedObject(updatedOrder);
    }
}
