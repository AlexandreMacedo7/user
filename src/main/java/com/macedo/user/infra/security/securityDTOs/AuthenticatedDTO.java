package com.macedo.user.infra.security.securityDTOs;

import jakarta.validation.constraints.NotBlank;

public record AuthenticatedDTO(@NotBlank String login,@NotBlank String password) {
}
