package com.MiniShopping.mini_shopping.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @NotBlank
    @Schema(example = "1", description = "Unique identifier for the order item")
    private Long id;

    @Schema(example = "1", description = "ID of the product")
    private Long productId;

    @Schema(example = "2", description = "Quantity of the product")
    private Integer quantity;

    @Schema(example = "19.99", description = "Price of the product")
    private Double price;
}
