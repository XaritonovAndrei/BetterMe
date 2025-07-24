package com.backend.BetterMe.Repository;


import com.backend.BetterMe.Model.WorkoutHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutHistoryRepo extends JpaRepository<WorkoutHistory, Long> {
    List<WorkoutHistory> findByExerciseIdAndDatePerformed(Long exerciseId, LocalDate date);
    List<WorkoutHistory> findByDatePerformed(LocalDate date);
}