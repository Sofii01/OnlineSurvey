package com.online.survey.requests;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequest {
    @NotBlank(message = "title cannot be null")
    private String title;
    @NotBlank(message = "description cannot be null")
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiredAt;


    //questionRequest??

}
