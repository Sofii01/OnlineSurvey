package com.online.survey.models;

import com.online.survey.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_model")
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    @Enumerated(EnumType.STRING)
    private QuestionType type;
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyModel survey;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionModel> options;

    private boolean required;
    private int order;

}
