package com.MiniShopping.mini_shopping.domain.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotBlank
    @Schema(example = "1", description = "Unique identifier for the order")
    private Long id;

    @Schema(example = "1", description = "ID of the user who placed the order")
    private Long userId;

    @Schema(example = "john.doe@example.com", description = "Username of the user who placed the order")
    private String username;

    @Schema(example = "99.99", description = "Total amount for the order")
    private Double totalAmount;

    @Schema(example = "PENDING", description = "Current status of the order")
    private String status;

    @Schema(example = "[{\"productId\":1,\"quantity\":2}]", description = "List of items in the order")
    private List<OrderItemDTO> items = new ArrayList<>();
}
