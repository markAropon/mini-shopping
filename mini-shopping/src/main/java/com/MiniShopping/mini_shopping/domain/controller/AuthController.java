package com.MiniShopping.mini_shopping.domain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MiniShopping.mini_shopping.domain.dto.AuthResponseDTO;
import com.MiniShopping.mini_shopping.domain.dto.LoginDTO;
import com.MiniShopping.mini_shopping.domain.dto.UserRegistrationDTO;
import com.MiniShopping.mini_shopping.domain.service.AuthService;       

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "[PUBLIC] User registration and authentication endpoints")
public class AuthController {
    private final AuthService authService;
    @Operation(summary = "Register a new user", description = "Endpoint for user registration")
    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody UserRegistrationDTO userRegistration){
     String response = authService.userRegistration(userRegistration);  
        
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Login a user", description = "Endpoint for user login")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO login) {
        AuthResponseDTO response = authService.login(login);
        return ResponseEntity.ok(response);
    }
}
