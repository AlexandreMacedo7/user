package com.macedo.user.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateLoginDTO(@NotBlank String login, @NotBlank String password) {
}
