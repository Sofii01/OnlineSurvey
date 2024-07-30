package com.online.survey.services;

import com.online.survey.models.UserModel;
import com.online.survey.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getUsers(){
        return userRepository.findAll();
    }


}
