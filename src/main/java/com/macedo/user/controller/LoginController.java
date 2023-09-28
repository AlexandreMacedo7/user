package com.macedo.user.controller;

import com.macedo.user.infra.security.securityDTOs.AuthenticatedDTO;
import com.macedo.user.infra.security.securityDTOs.DataTokenJWTDTO;
import com.macedo.user.model.Login;
import com.macedo.user.model.dto.CreateLoginDTO;
import com.macedo.user.infra.security.securityService.AuthenticatorService;
import com.macedo.user.infra.security.securityService.TokenService;
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
    TokenService tokenService;

    @Autowired
    private AuthenticatorService service;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticatedDTO data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((Login) authentication.getPrincipal());
        return ResponseEntity.ok(new DataTokenJWTDTO(tokenJWT));
    }

    @PostMapping("/create")
    public ResponseEntity createLogin(@RequestBody @Valid CreateLoginDTO dto){
        var login = service.createLogin(dto);
        return ResponseEntity.ok().body(login);
    }
}
