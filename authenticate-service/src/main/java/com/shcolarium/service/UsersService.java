package com.shcolarium.service;

import com.shcolarium.persistence.Users;
import com.shcolarium.repository.UsersRepository;
import com.shcolarium.security.TokenService;
import org.antlr.v4.runtime.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String create (String login,String password){
        Users user = new Users();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setActived(true);
        user.setCreatedDate(LocalDateTime.now());
        usersRepository.save(user);
        return "User saved";
    }

    public String generateToken(String username) {
        return tokenService.generateToken(username);
    }

    public void validateToken(String token) {
        tokenService.validateToken(token);
    }

    public boolean findByLogin (String login){
        Optional<Users> byLogin = usersRepository.findByLogin(login);
        if (byLogin.isPresent()){
            return true;
        }
        return false;
    }


}
