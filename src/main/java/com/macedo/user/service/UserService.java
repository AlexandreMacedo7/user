package com.macedo.user.service;

import com.macedo.user.mapper.UserMapper;
import com.macedo.user.model.User;
import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.DetailsUserDto;
import com.macedo.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }
    public User createUser(CreateUserDto createUserDto){
        var user = userMapper.toEntity(createUserDto);
        return repository.save(user);
    }

    public DetailsUserDto getUserById(Long id){
        var user = repository.findById(id);
        if (user.isPresent()){
            var dto = userMapper.toDTO(user.get());
            return dto;
        }else {
            throw new EntityNotFoundException("User not found with ID: "+ id);
        }

    }
}
