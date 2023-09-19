package com.macedo.user.mapper;

import com.macedo.user.model.User;
import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.DetailsUserDTO;
import com.macedo.user.model.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.name());
        user.setEmail(createUserDto.email());

        return user;
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(user);
    }
    public DetailsUserDTO toDetailsDTO(User user){
        return new DetailsUserDTO(user);
    }
}
