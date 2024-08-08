package com.online.survey.services;

import com.online.survey.dtos.SurveyDto;
import com.online.survey.enums.Role;
import com.online.survey.enums.SurveyStatus;
import com.online.survey.exceptions.CustomerAlreadyExistsException;
import com.online.survey.models.CreatorModel;
import com.online.survey.models.SurveyModel;
import com.online.survey.repositories.CreatorRepository;
import com.online.survey.repositories.SurveyRepository;
import com.online.survey.repositories.UserRepository;
import com.online.survey.requests.SurveyRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final CreatorRepository creatorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    //create new survey and set creator
    //still define request attributes
    public SurveyDto createSurvey(SurveyRequest request, Long idCreator){
        var creator = creatorRepository.findById(idCreator).get();

        //verify if exists other survey with the same title
        SurveyModel surveySameTitle = surveyRepository.findByTitle(request.getTitle());
        if (surveySameTitle == null){
            throw new CustomerAlreadyExistsException("Title of Survey already exists");
        }
        SurveyModel survey = modelMapper.map(request, SurveyModel.class);

        //create an url


        //set creator
        survey.setCreator(creator);
        surveyRepository.save(survey);
        return modelMapper.map(survey, SurveyDto.class);

    }
    //find all surveys by title and approved
    //Survey dto is generic, can use in this case
    public List<SurveyDto> getSurveyByTitle(String title){
        List<SurveyModel> list = surveyRepository.findByTitleAndStatus(title, SurveyStatus.Posted);

        return list.stream().map(survey -> modelMapper.map(survey, SurveyDto.class)).collect(Collectors.toList());
    }

    //find surveys recently posted
    //don´t define dto to use yet
    //
    public List<SurveyDto> getSurveyPostedOrderDesc(){
        List<SurveyModel> list = surveyRepository.findByStatusOrderByPostedAtDesc(SurveyStatus.Posted);
        return list.stream().map(surveyModel -> modelMapper.map(surveyModel, SurveyDto.class)).collect(Collectors.toList());
    }




    //like creator or administrator, I can get all surveys by different status
    //don´t define dto to use yet

    public List<SurveyDto> getSurveyByStatus(SurveyStatus status){
        List<SurveyModel> list = surveyRepository.findByStatus(status);
        return list.stream().map(surveyModel -> modelMapper.map(surveyModel, SurveyDto.class)).collect(Collectors.toList());
    }
    //update survey with Survey request or new request?
    // should receive id and find by id
    //


    //only administrator could to change survey status
    //define dto to use
    public String changeStatus(SurveyStatus status, Long idUser, Long idSurvey){
        var user = userRepository.findById(idUser);
        Role role = user.get().getRole();
        if (!role.equals(Role.Administrator)){
            return "Unauthorized User";
        }
        var survey = surveyRepository.findById(idSurvey);
        SurveyStatus surveyStatus = survey.get().getStatus();
        if (surveyStatus.equals(status)){
            return "Cannot be exchanged for the same state";
        }
        survey.get().setStatus(status);
        surveyRepository.save(survey.get());
        return "Successful change of status";
        //if survey status is posted, I should create a new date to postedAt
        //if survey status is finished, I should set deleted flag to true
    }




}
