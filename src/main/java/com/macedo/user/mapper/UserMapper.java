package com.macedo.user.mapper;

import com.macedo.user.model.User;
import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.DetailsUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.name());
        user.setEmail(createUserDto.email());

        return user;
    }

    public DetailsUserDto toDTO(User user) {
        DetailsUserDto dto = new DetailsUserDto(user);
        return dto;
    }
}
