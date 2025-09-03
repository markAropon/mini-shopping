package com.MiniShopping.mini_shopping.domain.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @Schema(example = "1", description = "Unique identifier for the order")
    private Long id;

    @NotNull(message = "User ID is required")
    @Schema(example = "1", description = "ID of the user who placed the order")
    private Long userId;

    @NotBlank(message = "Username is required")
    @Schema(example = "john.doe@example.com", description = "Username of the user who placed the order")
    private String username;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be greater than 0")
    @Schema(example = "99.99", description = "Total amount for the order")
    private Double totalAmount;

    @NotBlank(message = "Status is required")
    @Schema(example = "PENDING", description = "Current status of the order")
    private String status;

    @NotEmpty(message = "Order must have at least one item")
    @Valid
    @Schema(example = "[{\"productId\":1,\"quantity\":2}]", description = "List of items in the order")
    private List<OrderItemDTO> items = new ArrayList<>();
}
