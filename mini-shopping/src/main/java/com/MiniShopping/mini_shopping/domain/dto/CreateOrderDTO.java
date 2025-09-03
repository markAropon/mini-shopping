package com.MiniShopping.mini_shopping.domain.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    @NotEmpty(message = "Order must have at least one item")
    @Valid
    @Schema(description = "List of items to be ordered", example = "[{\"productId\":1,\"quantity\":2}]")
    private List<OrderItemDTO> items = new ArrayList<>();
}
