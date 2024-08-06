package com.online.survey.repositories;

import com.online.survey.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Boolean existsByEmail(String email);
    Optional<UserModel> findByUsername(String name);
    UserModel findByEmail(String email);
}
