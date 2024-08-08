package com.online.survey.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.online.survey.models.QuestionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDto {
    private String title;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate postedAt;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiredAt;
    //private CreatorDto creator;
    private List<QuestionDto> questions;
}
