package com.macedo.user.controller;

import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.DetailsUserDto;
import com.macedo.user.model.dto.UpdatedUserDataDto;
import com.macedo.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateUserDto createUserDto, UriComponentsBuilder builder) {
        var user = service.createUser(createUserDto);
        var uri = builder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUserDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        var user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<DetailsUserDto>> list(@PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
        var page = service.listUsers(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid UpdatedUserDataDto dataDto) {
        var user = service.updateUser(dataDto);
        return ResponseEntity.ok(new DetailsUserDto(user));
    }

}
