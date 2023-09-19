package com.macedo.user.model.dto;

import com.macedo.user.model.User;

public record UserDTO(Long id, String name, String email) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
