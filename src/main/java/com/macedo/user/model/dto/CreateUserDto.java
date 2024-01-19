package com.macedo.user.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(@NotBlank String name, @NotBlank String email) {
}
