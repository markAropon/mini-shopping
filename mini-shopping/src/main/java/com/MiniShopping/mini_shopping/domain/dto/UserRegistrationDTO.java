package com.MiniShopping.mini_shopping.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    @NotBlank (message = "First name is required")
    @Schema(example = "John")
    private String firstName;
    @NotBlank (message = "Last name is required")
    @Schema(example = "Doe")
    private String lastName;
    @NotBlank (message = "Username is required")
    @Schema(example = "johndoe")
    private String username;
    @NotBlank (message = "Email is required")
    @Schema(example = "john.doe@example.com")
    private String email;
    @NotBlank (message = "Password is required")
    @Schema(example = "password123")
    private String password;
}
