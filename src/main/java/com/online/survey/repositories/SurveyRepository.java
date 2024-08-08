package com.online.survey.repositories;

import com.online.survey.dtos.SurveyDto;
import com.online.survey.enums.SurveyStatus;
import com.online.survey.models.SurveyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyModel, Long> {
    List<SurveyModel> findByTitleAndStatus(String title, SurveyStatus status);

    List<SurveyModel> findByStatusOrderByPostedAtDesc(SurveyStatus status);

    List<SurveyModel> findByStatus(SurveyStatus status);

    SurveyModel findByTitle(String title);
}
