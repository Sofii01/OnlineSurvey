package com.online.survey.controllers;

import com.online.survey.dtos.CreatorDto;
import com.online.survey.models.CreatorModel;
import com.online.survey.requests.CreatorRequest;
import com.online.survey.services.CreatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creator")
@RequiredArgsConstructor
public class CreatorController {
    private final CreatorService creatorService;
    @PostMapping("/post")
    public ResponseEntity<?> createNewCreator(@Valid @RequestBody CreatorRequest request){
        CreatorDto creatorModel = creatorService.createCreator(request);
        return ResponseEntity.ok(creatorModel);

    }
    @GetMapping("/getall")
    public ResponseEntity<?> getCreators(){
        List<CreatorDto> list = creatorService.getAllCreators();
        return ResponseEntity.ok(list);
    }
}
