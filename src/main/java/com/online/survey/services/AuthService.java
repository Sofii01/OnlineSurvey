package com.online.survey.services;

import com.online.survey.dtos.AuthDto;
import com.online.survey.dtos.UserDto;
import com.online.survey.models.UserModel;
import com.online.survey.repositories.UserRepository;
import com.online.survey.requests.LoginRequest;
import com.online.survey.requests.RegisterRequest;
import com.online.survey.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthDto registerUser(RegisterRequest registerRequest) {
        UserModel user = userService.createUser(registerRequest);
        String token = jwtService.getToken(user);
        return AuthDto.builder().token(token).build();
    }

    public AuthDto login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserModel user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthDto.builder()
                .token(token)
                .build();

    }
}
