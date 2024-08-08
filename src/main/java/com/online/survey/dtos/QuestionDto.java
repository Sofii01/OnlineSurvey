package com.online.survey.dtos;

import com.online.survey.enums.QuestionType;
import com.online.survey.models.OptionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String text;
    private QuestionType type;
    //List<OptionDto> options;
    private boolean required;
}
