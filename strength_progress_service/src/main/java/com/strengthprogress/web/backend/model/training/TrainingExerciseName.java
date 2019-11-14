package com.strengthprogress.web.backend.model.training;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class TrainingExerciseName {
    @Id @GeneratedValue
    private Long id;
    private String name;
}
