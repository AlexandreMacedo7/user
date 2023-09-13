package com.macedo.user.model.dto;

import com.macedo.user.model.User;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(@NotNull String name, @NotNull String email) {
}
