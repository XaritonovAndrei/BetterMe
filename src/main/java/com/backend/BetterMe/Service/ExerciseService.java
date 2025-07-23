package com.backend.BetterMe.Service;

import com.backend.BetterMe.Model.Exercise;
import com.backend.BetterMe.Model.ExerciseDetail;
import com.backend.BetterMe.Model.ExerciseTracker;
import com.backend.BetterMe.Repository.ExerciseDetailRepo;
import com.backend.BetterMe.Repository.ExerciseRepo;
import com.backend.BetterMe.Repository.ExerciseTrackerRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;
    private final ExerciseDetailRepo exDetailRepo;
    private final ExerciseTrackerRepo exTrackerRepo;

//    ! @RequiredArgsConstructor conflict?
//    public ExerciseService(ExerciseRepo exerciseRepo, ExerciseDetailRepo detailRepo) {
//        this.exerciseRepo = exerciseRepo;
//        this.exDetailRepo = detailRepo;
//    }

    /**
     * Adds a new Exercise to the master list
     */
    public Exercise addExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    /**
     * Lists all Exercises from master list
     */
    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
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
     * Add exercise to today's workout
     */
    public void addToTodayWorkout(Long exerciseId) {
        Exercise ex = exerciseRepo.findById(exerciseId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Exercise not found: " + exerciseId));
        ExerciseDetail exDetail = new ExerciseDetail();
        exDetail.setExercise(ex);
//        exDetail.setDate(LocalDate.now());
        exDetailRepo.save(exDetail);
    }

    /**
     * show today's exercises
     */
    public List<ExerciseDetail> getTodayExercises() {
        return exDetailRepo.findAll();
    }

    @Transactional
    public void deleteTodayEntryByExerciseId(Long exerciseId) {
        List<ExerciseDetail> details = exDetailRepo.findAll();
        List<ExerciseDetail> toDelete = details.stream()
                .filter(d -> d.getExercise().getId().equals(exerciseId))
                .collect(Collectors.toList());
        if (toDelete.isEmpty()) {
            throw new IllegalArgumentException("No entry for exercise ID " + exerciseId + " found for today");
        }
        toDelete.forEach(exDetailRepo::delete);
    }

    /**
     * Remove today's workout entries for a given exercise ID.
     * Deletes all ExerciseDetail records for today matching the exercise.
     */
    @Transactional
    public ExerciseTracker markExerciseCompleted(Long exerciseId, boolean completed) {
        LocalDate today = LocalDate.now();
        List<ExerciseTracker> existing =
                exTrackerRepo.findByExerciseIdAndDate(exerciseId, today);

        ExerciseTracker tracker;
        if (existing.isEmpty()) {
            Exercise ex = exerciseRepo.findById(exerciseId)
                    .orElseThrow(() -> new IllegalArgumentException("Exercise not found: " + exerciseId));
            tracker = new ExerciseTracker();
            tracker.setExercise(ex);
            tracker.setDate(today);
        } else {
            tracker = existing.get(0);
        }

        tracker.setCompleted(completed);
        return exTrackerRepo.save(tracker);
    }

    /**
     * List all completion records for today.
     */
    public List<ExerciseTracker> getTodayCompletion() {
        return exTrackerRepo.findByDate(LocalDate.now());
    }
}