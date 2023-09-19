package com.macedo.user.model.dto;

import jakarta.validation.constraints.NotNull;

public record CreateLoginDTO(@NotNull String login, @NotNull String password) {
}
