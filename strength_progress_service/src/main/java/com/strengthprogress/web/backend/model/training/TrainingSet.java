package com.strengthprogress.web.backend.model.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrainingSet {
    @Id @GeneratedValue
    private Long id;
    private Integer reps;
    private Double weight;

    public Double getVolume(){
        return weight * reps;
    }

    public Double getMax(){
        return 0D;
    }

}
