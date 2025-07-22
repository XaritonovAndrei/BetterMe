package com.backend.BetterMe.Service;

import com.backend.BetterMe.Model.Exercise;
import com.backend.BetterMe.Repository.ExerciseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;

    /**
     * Adds a new Exercise.
     * @param exercise the exercise to save
     * @return the saved Exercise
     */
    public Exercise addExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    /**
     * Retrieves all Exercises.
     * @return list of all exercises
     */
    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }
}