package com.backend.BetterMe.Service;

import com.backend.BetterMe.Model.Exercise;
import com.backend.BetterMe.Model.WorkoutHistory;
import com.backend.BetterMe.Repository.ExerciseRepo;
import com.backend.BetterMe.Repository.WorkoutHistoryRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {
    private final WorkoutHistoryRepo workoutHistoryRepo;
    private final ExerciseRepo exerciseRepo;

    /**
     * Adds a new Exercise to the master list
     */
    public Exercise addExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    /**
     * Delete selected exercise from master list
     */
    @Transactional
    public void deleteExercise(Long id) {
        if (!exerciseRepo.existsById(id)) {
            throw new IllegalArgumentException("Exercise with ID " + id + " not found");
        }
        exerciseRepo.deleteById(id);
    }

    /**
     * Lists all Exercises from master list
     */
    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }

    /**
     * Record a completed workout set for today.
     */
    public WorkoutHistory recordWorkout(Long exerciseId, int reps, int sets, double weight) {
        Exercise ex = exerciseRepo.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown exercise " + exerciseId));
        WorkoutHistory historyEntry = new WorkoutHistory();
        historyEntry.setExercise(ex);
        historyEntry.setReps(reps);
        historyEntry.setSets(sets);
        historyEntry.setWeight(weight);
        historyEntry.setDatePerformed(LocalDate.now());

        return workoutHistoryRepo.save(historyEntry);
    }

    /**
     * List all sets performed for a given exercise on a given date.
     */
    public List<WorkoutHistory> getSetsByDate(Long exerciseId, LocalDate date) {
        return workoutHistoryRepo.findByExerciseIdAndDatePerformed(exerciseId, date);
    }

    /**
     * List all sets performed today across all exercises.
     */
    public List<WorkoutHistory> getTodayEntries() {
        return workoutHistoryRepo.findByDatePerformed(LocalDate.now());
    }

    /**
     * List all exercises, performed on specified date
     */
    public List<WorkoutHistory> getAllWorkoutByDate(LocalDate date) {
        return workoutHistoryRepo.findByDatePerformed(date);
    }
}