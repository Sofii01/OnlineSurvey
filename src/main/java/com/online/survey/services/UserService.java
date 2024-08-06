package com.online.survey.services;

import com.online.survey.dtos.UserDto;
import com.online.survey.enums.Role;
import com.online.survey.exceptions.CustomerAlreadyExistsException;
import com.online.survey.models.UserModel;
import com.online.survey.repositories.UserRepository;
import com.online.survey.requests.RegisterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public List<UserDto> getUsers(){
        List<UserModel> list = userRepository.findAll();
        return list.stream().map(
                user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
    //get one user by name or id


    //create new user
    public UserModel createUser(RegisterRequest registerRequest){
        UserModel user = new UserModel();
        user.setUsername(registerRequest.getUsername());
        user.setLastName(registerRequest.getLastname());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());


        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        boolean existUser = userRepository.existsByEmail(user.getEmail());
        if(existUser){
            throw new CustomerAlreadyExistsException("User already exists with email: " + user.getEmail());
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;

    }

    //delete user
    public UserModel deleteUser(Long id){
        var user = userRepository.findById(id);
        user.get().setDeleted(true);
        userRepository.save(user.get());
        return user.get();

    }


    //update user


}
