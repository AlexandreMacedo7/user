package com.macedo.user.model.dto;

import jakarta.validation.constraints.NotNull;

public record UpdatedUserDataDto(@NotNull Long id, String name, String email) {
}
