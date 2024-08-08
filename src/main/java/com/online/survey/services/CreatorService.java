package com.online.survey.services;

import com.online.survey.dtos.CreatorDto;
import com.online.survey.dtos.UserDto;
import com.online.survey.enums.Role;
import com.online.survey.exceptions.CustomerAlreadyExistsException;
import com.online.survey.models.CreatorModel;
import com.online.survey.models.UserModel;
import com.online.survey.repositories.CreatorRepository;
import com.online.survey.repositories.UserRepository;
import com.online.survey.requests.CreatorRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatorService {
    private final CreatorRepository creatorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public CreatorDto createCreator(CreatorRequest request){
        Optional<UserModel> user = userRepository.findByUsername(request.getRegisterRequest().getUsername());
        if (user.isPresent()){
           throw new CustomerAlreadyExistsException("This user already exists");
        }
        // Map the CreatorRequest to CreatorModel
        CreatorModel creatorModel = modelMapper.map(request, CreatorModel.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        creatorModel.setUsername(request.getRegisterRequest().getUsername());
        creatorModel.setEmail(request.getRegisterRequest().getEmail());
        creatorModel.setPassword(passwordEncoder.encode(request.getRegisterRequest().getPassword()));
        creatorModel.setLastName(request.getRegisterRequest().getLastname());
        creatorModel.setRole(Role.Creator);

        creatorRepository.save(creatorModel);

        // Manually map UserModel to UserDto
        UserDto userDto = modelMapper.map(creatorModel, UserDto.class);

        // Map CreatorModel to CreatorDto and set the userDto
        CreatorDto creatorDto = modelMapper.map(creatorModel, CreatorDto.class);
        creatorDto.setUserDto(userDto);
        return creatorDto;

    }

    public List<CreatorDto> getAllCreators(){

        List<CreatorModel> list = creatorRepository.findAll();
        return list.stream().map(creatorModel -> {

            // Manually map UserModel fields to UserDto
            UserDto userDto = new UserDto();
            userDto.setId(creatorModel.getId());
            userDto.setUsername(creatorModel.getUsername());
            userDto.setEmail(creatorModel.getEmail());

            userDto.setLastName(creatorModel.getLastName());
            userDto.setRole(creatorModel.getRole());

            CreatorDto creatorDto = modelMapper.map(creatorModel, CreatorDto.class);
            creatorDto.setUserDto(userDto);
            return creatorDto;

        }).collect(Collectors.toList());
    }

    //admi
    //change creators status and set feedback


}
