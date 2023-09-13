package com.macedo.user.model.dto;

import jakarta.validation.constraints.NotNull;

public record CreateUserDto(@NotNull String name, @NotNull String email) {
}
