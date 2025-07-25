package com.backend.BetterMe.Controller;


import com.backend.BetterMe.Model.Exercise;
import com.backend.BetterMe.Model.WorkoutHistory;
import com.backend.BetterMe.Service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * Get workout history for a specific date (default => today).
     */
    @GetMapping
    public List<WorkoutHistory> showWorkoutPage(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now();
        return workoutService.getAllWorkoutByDate(targetDate);
    }

    /**
     * Add a new exercise to the master list.
     */
    @PostMapping("/addExercise")
    public Exercise addExercise(@RequestBody Exercise exercise) {
        return workoutService.addExercise(exercise);
    }

    /**
     * Delete an exercise from the master list.
     */
    @DeleteMapping("/deleteExercise/{id}")
    public void deleteExercise(@PathVariable("id") Long id) {
        workoutService.deleteExercise(id);
    }

    /**
     * Record a completed workout set.
     * Method takes JSON file with keys: exerciseId, reps, sets, weight
     */
    @PostMapping("/recordWorkout")
    public WorkoutHistory recordWorkout(@RequestBody Map<String, Object> payload) {
        Long exerciseId = ((Number) payload.get("exerciseId")).longValue();
        int reps = ((Number) payload.get("reps")).intValue();
        int sets = ((Number) payload.get("sets")).intValue();
        double weight = ((Number) payload.get("weight")).doubleValue();
        return workoutService.recordWorkout(exerciseId, reps, sets, weight);
    }

    /**
     * List today's workout plan (or another specific date)
     */
    @GetMapping("/todayWorkout")
    public List<WorkoutHistory> todayList(
            @RequestParam(required = false)
            Long exerciseId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        if (exerciseId != null && date != null) {
            return workoutService.getSetsByDate(exerciseId, date);
        }
        return workoutService.getTodayEntries();
    }

    /**
     * List all workout history by arbitrary date.
     */
    @GetMapping("/workoutByDate")
    public List<WorkoutHistory> listAllByDate(
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Incorrect date");
        }
        return workoutService.getAllWorkoutByDate(date);
    }

}