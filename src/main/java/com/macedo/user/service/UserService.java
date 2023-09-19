package com.macedo.user.service;

import com.macedo.user.mapper.UserMapper;
import com.macedo.user.model.User;
import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.DetailsUserDTO;
import com.macedo.user.model.dto.UpdatedUserDataDto;
import com.macedo.user.model.dto.UserDTO;
import com.macedo.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(CreateUserDto createUserDto) {
        var user = userMapper.toEntity(createUserDto);
        repository.save(user);
        return userMapper.toDTO(user);
    }

    public DetailsUserDTO getUserById(Long id) {
        var user = repository.findById(id);
        if (user.isPresent()) {
            return userMapper.toDetailsDTO(user.get());
        } else {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }

    }

    public Page<DetailsUserDTO> listUsers(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(DetailsUserDTO::new);
    }

    public void deleteUser(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
    }

    public User updateUser(UpdatedUserDataDto dataDto) {
        var user = repository.findById(dataDto.id()).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dataDto.id()));
        BeanUtils.copyProperties(dataDto, user, "id");
        return repository.save(user);
    }
}
