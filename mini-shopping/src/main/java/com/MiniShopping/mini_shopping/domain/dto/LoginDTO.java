package com.MiniShopping.mini_shopping.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Username or email is required")
    @Schema(example = "john.doe@example.com")
    private String usernameOrEmail;
    @Schema(example = "password123")
    @NotBlank(message = "Password is required")
    private String password;
}
