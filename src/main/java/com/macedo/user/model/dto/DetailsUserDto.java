package com.macedo.user.model.dto;

import com.macedo.user.model.User;

public record DetailsUserDto(Long id, String name, String email) {

    public DetailsUserDto(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
