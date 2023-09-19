package com.macedo.user.model.dto;

import com.macedo.user.model.User;

public record DetailsUserDTO(Long id, String name, String email) {

    public DetailsUserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
