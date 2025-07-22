package com.backend.BetterMe.Controller;


import com.backend.BetterMe.Model.Exercise;
import com.backend.BetterMe.Repository.ExerciseRepo;
import com.backend.BetterMe.Service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    private final ExerciseService exerciseService;

    /**
     * Create a new Exercise.
     * @param exercise body contains name of the exercise
     * @return saved Exercise
     */
    @PostMapping
    public Exercise addExercise(@RequestBody Exercise exercise) {
        return exerciseService.addExercise(exercise);
    }

    /**
     * Retrieve all exercises.
     * @return list of all Exercises
     */
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }
}
