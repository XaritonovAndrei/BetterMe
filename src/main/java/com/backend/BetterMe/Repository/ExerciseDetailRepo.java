package com.backend.BetterMe.Repository;

import com.backend.BetterMe.Model.ExerciseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExerciseDetailRepo extends JpaRepository<ExerciseDetail, Long> {
    // find all entries
//    List<ExerciseDetail> findByName(LocalDate date);
//    List<ExerciseDetail> findByDate(LocalDate date);
    List<ExerciseDetail> findByExerciseId(Long exerciseId);
}

