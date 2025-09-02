package com.MiniShopping.mini_shopping.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @Schema(example = "1", description = "Unique identifier for the product")
    private Long id;
    
    // used for the product data feeding
    public ProductDTO(String name, String description, Double price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    @Schema(example = "Laptop", description = "Name of the product")
    private String name;

    @Schema(example = "High-performance laptop", description = "Description of the product")
    private String description;

    @Schema(example = "999.99", description = "Price of the product")
    private Double price;

    @Schema(example = "10", description = "Stock quantity of the product")
    private Integer stock;
}
