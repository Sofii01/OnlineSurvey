package com.online.survey.loader;

import com.online.survey.enums.Role;
import com.online.survey.models.UserModel;
import com.online.survey.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{
        if (userRepository.count() == 0){
            List<UserModel> users =new ArrayList<>();
            users.add(new UserModel("sofi@gmail.com", "admin123", "Sofia", "Bruno", false, Role.Administrator, LocalDate.now()));
            users.add(new UserModel("celeste@gmail.com", "creator123", "Celeste", "Alcota", false, Role.Creator, LocalDate.now()));
            users.add(new UserModel("matias@gmail.com", "creator123", "Matias", "Gomez", false, Role.Creator, LocalDate.now()));
            users.add(new UserModel("liliana@gmail.com", "surveyed123", "Liliana", "Juarez", false, Role.Surveyed, LocalDate.now()));
            users.add(new UserModel("mauricio@gmail.com", "surveyed123", "Mauricio", "Moltovan", false, Role.Surveyed, LocalDate.now()));
            userRepository.saveAll(users);
        }
    }
}
