package com.strengthprogress.web.backend.model.training;

import com.strengthprogress.web.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Training {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private LocalDate date;
    @OneToMany
    private List<TrainingExercise> trainingExercises;
}
