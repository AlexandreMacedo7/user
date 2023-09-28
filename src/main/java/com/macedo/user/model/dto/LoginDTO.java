package com.macedo.user.model.dto;

import com.macedo.user.model.Login;

public record LoginDTO(Long id, String login) {

    public LoginDTO(Login login){
        this(login.getId(), login.getUsername());
    }
}
