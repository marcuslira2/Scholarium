package com.shcolarium.controller;

import com.shcolarium.dto.CreateUserRecord;
import com.shcolarium.persistence.Users;
import com.shcolarium.security.TokenService;
import com.shcolarium.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsersController {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;



    public UsersController(UsersService usersService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody CreateUserRecord dto){
        boolean userExists = usersService.findByLogin(dto.login());
        if(userExists){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersService.create(dto.login(), dto.password()));
    }

    @PostMapping("/token")
    public String getToken(@RequestBody CreateUserRecord authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password()));
        if (authenticate.isAuthenticated()) {
            return usersService.generateToken(authRequest.login());
        } else {
            throw new RuntimeException("invalid access");
        }
    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        try {
            tokenService.validateToken(token);
            return "Token is valid";
        }catch (Exception e){
            return "Token is invalid";
        }

    }

}
