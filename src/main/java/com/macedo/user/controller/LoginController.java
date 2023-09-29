package com.macedo.user.controller;

import com.macedo.user.infra.security.securityDTOs.AuthenticatedDTO;
import com.macedo.user.infra.security.securityService.LoginService;
import com.macedo.user.model.dto.CreateLoginDTO;
import com.macedo.user.model.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping("/create")
    public ResponseEntity createLogin(@RequestBody @Valid CreateLoginDTO dto, UriComponentsBuilder builder) {
        var login = loginService.createLogin(dto);
        return createResponseSuccess(login, builder);
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticatedDTO data) {
        var tokenJWT = loginService.loginJWT(data);
        return ResponseEntity.ok(tokenJWT);
    }

    private ResponseEntity createResponseSuccess(LoginDTO dto, UriComponentsBuilder builder) {
        var uri = builder.path("/create/id").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
