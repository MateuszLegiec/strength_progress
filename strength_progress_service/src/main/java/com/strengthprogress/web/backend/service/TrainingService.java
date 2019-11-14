package com.strengthprogress.web.backend.service;

import com.strengthprogress.web.backend.dto.ChartData;
import com.strengthprogress.web.backend.expection.ObjectNotFoundException;
import com.strengthprogress.web.backend.model.User;
import com.strengthprogress.web.backend.model.training.Training;
import com.strengthprogress.web.backend.model.training.TrainingExerciseName;
import com.strengthprogress.web.backend.model.training.TrainingSet;
import com.strengthprogress.web.backend.repository.training.TrainingExerciseNameRepository;
import com.strengthprogress.web.backend.repository.training.TrainingExerciseRepository;
import com.strengthprogress.web.backend.repository.training.TrainingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingExerciseRepository exerciseRepository;
    private final TrainingExerciseNameRepository exerciseNameRepository;

    public TrainingService(TrainingRepository trainingRepository, TrainingExerciseRepository exerciseRepository, TrainingExerciseNameRepository exerciseNameRepository) {
        this.trainingRepository = trainingRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseNameRepository = exerciseNameRepository;
    }

    public Training getTraining(LocalDate date) throws ObjectNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return trainingRepository.findByDateAndUser(date, user).orElseThrow(() -> new ObjectNotFoundException(Training.class));
    }

    public void saveTraining(Training training){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        training.setUser(user);
        trainingRepository.save(training);
    }

    public List<TrainingExerciseName> getAllNames(){
        return exerciseNameRepository.findAll();
    }

    public List<ChartData> getChart(String exerciseName){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return exerciseRepository.findAllByName_NameAndTrainingUser(exerciseName,user)
                .stream()
                .map(trainingExercise -> {

                    LocalDate date = trainingExercise
                            .getTraining()
                            .getDate();

                    Double max = trainingExercise
                            .getSets()
                            .stream()
                            .mapToDouble(TrainingSet::getMax)
                            .max()
                            .orElse(0D);

                    Double volume = trainingExercise
                            .getSets()
                            .stream()
                            .mapToDouble(TrainingSet::getVolume)
                            .max()
                            .orElse(0D);

                    return new ChartData(date,max,volume);
                })
                .sorted(Comparator.comparing(ChartData::getDate))
                .collect(Collectors.toList());
    }

    public List<LocalDate> getUserFrequency(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return trainingRepository
                .findAllByUser(user)
                .stream()
                .map(Training::getDate)
                .collect(Collectors.toList());
    }
}
