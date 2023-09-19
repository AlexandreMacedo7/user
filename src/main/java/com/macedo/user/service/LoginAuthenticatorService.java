package com.macedo.user.service;

import com.macedo.user.model.Login;
import com.macedo.user.model.dto.CreateLoginDTO;
import com.macedo.user.repository.LoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginAuthenticatorService implements UserDetailsService {

    private final LoginRepository repository;

    public LoginAuthenticatorService(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userDetails) throws UsernameNotFoundException {
        return repository.findByLogin(userDetails);
    }

    public Login CreateLogin(CreateLoginDTO dto) {
        var login = new Login();
        login.setLogin(dto.login());
        login.setPassword(dto.password());
        return repository.save(login);
    }
}
