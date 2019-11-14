package com.strengthprogress.web.backend.controller;

import com.strengthprogress.web.backend.dto.ChartData;
import com.strengthprogress.web.backend.expection.ObjectNotFoundException;
import com.strengthprogress.web.backend.model.training.Training;
import com.strengthprogress.web.backend.model.training.TrainingExerciseName;
import com.strengthprogress.web.backend.service.TrainingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;

    @ExceptionHandler({ObjectNotFoundException.class})
    public String handleException(final Exception e){
        return e.getMessage();
    }

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/{date}")
    public Training getTraining(@PathVariable("date") LocalDate date){
        return trainingService.getTraining(date);
    }

    @PostMapping
    public void saveTraining(@RequestBody Training training){
        trainingService.saveTraining(training);
    }

    @GetMapping("/exercise/names")
    public List<TrainingExerciseName> getAllExerciseNames(){
        return trainingService.getAllNames();
    }

    @GetMapping("/{exercise-name}")
    public List<ChartData> getChartData(@PathVariable("exercise-name") String exerciseName){
        return trainingService.getChart(exerciseName);
    }

    @GetMapping("/frequency")
    public List<LocalDate> getUserFrequency(){
        return trainingService.getUserFrequency();
    }


}
