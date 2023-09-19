package com.macedo.user.controller;

import com.macedo.user.model.dto.AuthenticatedDataDTO;
import com.macedo.user.model.dto.CreateLoginDTO;
import com.macedo.user.service.LoginAuthenticatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private LoginAuthenticatorService service;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticatedDataDTO data){
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity createLogin(@RequestBody @Valid CreateLoginDTO dto){
        var login = service.CreateLogin(dto);
        return ResponseEntity.ok().body(login);
    }
}
