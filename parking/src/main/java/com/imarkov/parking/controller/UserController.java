package com.imarkov.parking.controller;

import com.imarkov.parking.model.TokenResponse;
import com.imarkov.parking.model.dto.LoginDTO;
import com.imarkov.parking.model.dto.RegisterDTO;
import com.imarkov.parking.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO registerDTO) {
         authService.register(registerDTO);

         return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
