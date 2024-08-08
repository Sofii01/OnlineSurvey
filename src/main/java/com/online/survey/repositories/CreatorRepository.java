package com.online.survey.repositories;

import com.online.survey.models.CreatorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends JpaRepository<CreatorModel, Long> {
}
