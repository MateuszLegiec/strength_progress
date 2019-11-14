package com.strengthprogress.web.backend.model.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrainingExercise {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private Training training;
    @OneToOne
    private TrainingExerciseName name;
    @OneToMany
    private List<TrainingSet> sets;

}
