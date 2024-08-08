package com.online.survey.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.online.survey.enums.CreatorStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creator_model")
public class CreatorModel extends UserModel{

    private String instagram;
    private String facebook;
    private String description;
    private String feedback;
    private String city;
    private String country;
    private CreatorStatus status = CreatorStatus.InReview;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyModel> surveysCreated;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt = LocalDate.now();

}
