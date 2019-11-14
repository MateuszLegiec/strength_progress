package com.strengthprogress.web.backend.repository.training;

import com.strengthprogress.web.backend.model.User;
import com.strengthprogress.web.backend.model.training.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training,Long> {
    Optional<Training> findByDateAndUser(LocalDate date, User user);
    List<Training> findAllByUser(User user);
}
