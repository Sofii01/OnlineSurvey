package com.online.survey.controllers;

import com.online.survey.dtos.AuthDto;
import com.online.survey.models.UserModel;
import com.online.survey.requests.LoginRequest;
import com.online.survey.requests.RegisterRequest;
import com.online.survey.services.AuthService;
import com.online.survey.services.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Data
public class AuthController {

    @Autowired
    private AuthService authService;

    // sign up function

    @PostMapping("/signup")
    public ResponseEntity<AuthDto> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthDto authDto = authService.registerUser(registerRequest);
        return ResponseEntity.ok(authDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthDto authDto = authService.login(loginRequest);
        return ResponseEntity.ok(authDto);
    }
}
