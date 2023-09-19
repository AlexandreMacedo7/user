package com.macedo.user.repository;

import com.macedo.user.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    UserDetails findByLogin(String login);
}
