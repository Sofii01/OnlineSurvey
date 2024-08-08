package com.online.survey.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.online.survey.enums.SurveyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "survey_model")
public class SurveyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String description;

    //every survey has one creator
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorModel creator;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate postedAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiredAt;
    @Enumerated(EnumType.STRING)
    private SurveyStatus status = SurveyStatus.InReview;
    //survey has one or many questions
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    List<QuestionModel> questionsList;
    private boolean deleted = false;

}
