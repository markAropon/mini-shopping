package com.MiniShopping.mini_shopping.domain.config;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.MiniShopping.mini_shopping.domain.dto.ProductDTO;
import com.MiniShopping.mini_shopping.domain.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductInitializer implements ApplicationRunner {

    private final ProductService productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ProductDTO> products = List.of(
            new ProductDTO("Apple iPhone 15 Pro", "Latest iPhone with A17 Pro chip, 48MP camera, and titanium design", 999.99, 50),
            new ProductDTO("Samsung Galaxy S25", "Flagship Android smartphone with 200MP camera and AI features", 899.99, 75),
            new ProductDTO("Sony WH-1000XM5", "Premium noise-cancelling wireless headphones with 30-hour battery life", 349.99, 100),
            new ProductDTO("MacBook Air M3", "Thin and light laptop with Apple M3 chip and 18-hour battery life", 1199.99, 35),
            new ProductDTO("Bose SoundLink Revolve", "Portable Bluetooth speaker with 360-degree sound", 199.99, 60),
            new ProductDTO("Ninja Air Fryer", "6.5-quart air fryer with multiple cooking functions", 129.99, 150),
            new ProductDTO("Dyson V12 Vacuum", "Cordless vacuum cleaner with HEPA filtration and powerful suction", 499.99, 60),
            new ProductDTO("Instant Pot Duo", "7-in-1 programmable pressure cooker, 6-quart", 89.99, 200),
            new ProductDTO("Ninja Foodi Grill", "Air fryer and grill combo with smart cooking technology", 249.99, 80),
            new ProductDTO("Nike Air Force 1", "Classic white sneakers with leather upper", 109.99, 80),
            new ProductDTO("Ray-Ban Aviator Sunglasses", "Classic aviator style with polarized lenses", 169.99, 45),
            new ProductDTO("Herschel Backpack", "Classic backpack with laptop sleeve and multiple compartments", 69.99, 120),
            new ProductDTO("Atomic Habits", "Book by James Clear on building good habits and breaking bad ones", 19.99, 200),
            new ProductDTO("The Psychology of Money", "Book by Morgan Housel about timeless lessons on wealth and happiness", 17.99, 150)
        );

        products.forEach(this::createProductIfNotExists);

        log.info("Initialized sample products in the database");
    }

    private void createProductIfNotExists(ProductDTO product) {

        if (!productService.existsByName(product.getName())) {
            productService.createProduct(product);
            log.info("Default product '{}' created successfully", product.getName());
        } else {
            log.info("Default product '{}' already exists", product.getName());
        }
    }
}
