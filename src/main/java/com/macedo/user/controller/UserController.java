package com.macedo.user.controller;

import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.DetailsUserDto;
import com.macedo.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateUserDto createUserDto, UriComponentsBuilder builder){
        var user = service.createUser(createUserDto);
        var uri = builder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUserDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        var user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
