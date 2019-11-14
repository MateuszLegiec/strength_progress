package com.strengthprogress.web.backend.repository.training;

import com.strengthprogress.web.backend.model.User;
import com.strengthprogress.web.backend.model.training.TrainingExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingExerciseRepository extends JpaRepository<TrainingExercise,Long> {
    List<TrainingExercise> findAllByName_NameAndTrainingUser(String exerciseName, User training_user);
}
