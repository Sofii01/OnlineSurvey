package com.online.survey.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreatorRequest {

    private RegisterRequest registerRequest;
    private String instagram;
    private String facebook;
    private String description;
    private String city;
    private String country;
}
