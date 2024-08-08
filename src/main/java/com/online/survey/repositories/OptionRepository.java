package com.online.survey.repositories;

import com.online.survey.models.OptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<OptionModel, Long> {
}
