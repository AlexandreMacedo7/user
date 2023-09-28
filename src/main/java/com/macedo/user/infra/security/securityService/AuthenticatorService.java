package com.macedo.user.infra.security.securityService;

import com.macedo.user.model.Login;
import com.macedo.user.model.dto.CreateLoginDTO;
import com.macedo.user.model.dto.LoginDTO;
import com.macedo.user.repository.LoginRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorService implements UserDetailsService {

    private final LoginRepository repository;

    public AuthenticatorService(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return repository.findByLogin(userName);
    }

    public LoginDTO createLogin(CreateLoginDTO dto) {
        var login = new Login();
        login.setLogin(dto.login());
        login.setPassword(dto.password());
        repository.save(login);
        return new LoginDTO(login);
    }
}
