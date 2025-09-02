package com.MiniShopping.mini_shopping.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiniShopping.mini_shopping.domain.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
