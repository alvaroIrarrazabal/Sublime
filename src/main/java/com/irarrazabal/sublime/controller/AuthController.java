package com.irarrazabal.sublime.controller;


import com.irarrazabal.sublime.DTO.AuthResponse;
import com.irarrazabal.sublime.DTO.LoginRequest;
import com.irarrazabal.sublime.model.User;
import com.irarrazabal.sublime.repository.UserRepository;
import com.irarrazabal.sublime.security.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public AuthController(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Usuario registrado correctamente";
    }




    @PostMapping("login")
    public AuthResponse  login(@RequestBody LoginRequest request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User dbUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(
                token,
                user.getRole().name(),
                jwtService.getExpirationTime()
        );



        }


    }




