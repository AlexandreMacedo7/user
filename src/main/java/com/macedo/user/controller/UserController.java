package com.macedo.user.controller;

import com.macedo.user.model.dto.CreateUserDto;
import com.macedo.user.model.dto.DetailsUserDTO;
import com.macedo.user.model.dto.UpdatedUserDataDto;
import com.macedo.user.model.dto.UserDTO;
import com.macedo.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateUserDto createUserDto, UriComponentsBuilder builder) {
        var userDTO = service.createUser(createUserDto);
        return createResponseSuccess(userDTO, builder);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        var user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<DetailsUserDTO>> list(@PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
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
        return ResponseEntity.ok(new DetailsUserDTO(user));
    }

    private ResponseEntity createResponseSuccess(UserDTO userDTO, UriComponentsBuilder builder) {
        var uri = builder.path("/users/id").buildAndExpand(userDTO.id()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

}
