package com.online.survey.controllers;


import com.online.survey.dtos.SurveyDto;
import com.online.survey.requests.SurveyRequest;
import com.online.survey.services.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    @PostMapping("/post/{idUser}")
    public ResponseEntity<?> createNewSurvey(@Valid @RequestBody SurveyRequest surveyRequest, @PathVariable Long idUser){
        SurveyDto survey = surveyService.createSurvey(surveyRequest, idUser);
        return ResponseEntity.ok(survey);

    }
}
