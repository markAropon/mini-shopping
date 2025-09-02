package com.MiniShopping.mini_shopping.domain.service;

import java.util.List;

import com.MiniShopping.mini_shopping.domain.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    boolean existsByName(String name);
}
