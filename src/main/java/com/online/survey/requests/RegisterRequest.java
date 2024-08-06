package com.online.survey.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
        @NotBlank(message = "username cannot be null")
        String username;
        @NotBlank
        String lastname;
        @NotNull(message = "email cannot be null")
        @Email
        String email;
        @NotBlank(message = "password cannot be null")
        String password;



}


