package com.macedo.user.infra.security.securityService;

import com.macedo.user.infra.security.securityDTOs.AuthenticatedDTO;
import com.macedo.user.infra.security.securityDTOs.DataTokenJWTDTO;
import com.macedo.user.infra.utility.PasswordHasher;
import com.macedo.user.model.Login;
import com.macedo.user.model.dto.CreateLoginDTO;
import com.macedo.user.model.dto.LoginDTO;
import com.macedo.user.repository.LoginRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final LoginRepository loginRepository;

    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService, LoginRepository loginRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.loginRepository = loginRepository;
    }

    public DataTokenJWTDTO loginJWT(AuthenticatedDTO data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((Login) authentication.getPrincipal());

        return new DataTokenJWTDTO(tokenJWT);
    }

    @Transactional
    public LoginDTO createLogin(CreateLoginDTO dto) {
        var login = new Login();
        login.setLogin(dto.login());
        var hashed = PasswordHasher.hashPassword(dto.password());
        login.setPassword(hashed);
        loginRepository.save(login);
        return new LoginDTO(login);
    }
}
