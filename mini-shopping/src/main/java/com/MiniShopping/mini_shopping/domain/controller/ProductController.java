package com.MiniShopping.mini_shopping.domain.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MiniShopping.mini_shopping.common.ApiResponse;
import com.MiniShopping.mini_shopping.common.DefaultResponse;
import com.MiniShopping.mini_shopping.domain.dto.ProductDTO;
import com.MiniShopping.mini_shopping.domain.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "[PUBLIC/ADMIN] Product management endpoints - GET is public, CREATE/UPDATE/DELETE require ADMIN role")
public class ProductController {

    private final ProductService productService;
    
    @Operation(
        summary = "Get all products", 
        description = "Retrieve all products (public endpoint)"
    )
    @GetMapping
    public ApiResponse<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return DefaultResponse.displayFoundObject(products);
    }
    
    @Operation(
        summary = "Get product by ID", 
        description = "Retrieve a specific product by its ID (public endpoint)"
    )
    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return DefaultResponse.displayFoundObject(product);
    }
    
    @Operation(
        summary = "Create a new product", 
        description = "Create a new product (ADMIN only)"
    )
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return DefaultResponse.displayCreatedObject(createdProduct);
    }
    
    @Operation(
        summary = "Update a product", 
        description = "Update an existing product (ADMIN only)"
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return DefaultResponse.displayUpdatedObject(updatedProduct);
    }
    
    @Operation(
        summary = "Delete a product", 
        description = "Delete a product by its ID (ADMIN only)"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return DefaultResponse.displayDeletedObject(null);
    }
}
