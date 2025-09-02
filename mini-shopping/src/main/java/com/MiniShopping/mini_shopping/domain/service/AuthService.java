package com.MiniShopping.mini_shopping.domain.service;

import com.MiniShopping.mini_shopping.domain.dto.AuthResponseDTO;
import com.MiniShopping.mini_shopping.domain.dto.LoginDTO;
import com.MiniShopping.mini_shopping.domain.dto.UserRegistrationDTO;

public interface AuthService {
    String userRegistration(UserRegistrationDTO userRegistrationDTO);
    AuthResponseDTO login(LoginDTO loginDTO);
}
