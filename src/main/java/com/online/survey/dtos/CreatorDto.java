package com.online.survey.dtos;

import com.online.survey.enums.CreatorStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatorDto {
    private UserDto userDto;
    private String instagram;
    private String facebook;
    private String description;
    private String city;
    private String country;
    private List<SurveyDto> surveys;

}
