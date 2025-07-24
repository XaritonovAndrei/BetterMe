//package com.backend.BetterMe.Repository;
//
//import com.backend.BetterMe.Model.ExerciseTracker;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public interface ExerciseTrackerRepo extends JpaRepository<ExerciseTracker, Long> {
//    List<ExerciseTracker> findByDateAndCompleted(LocalDate date, ExerciseTracker completed);
//    List<ExerciseTracker> findByDate(LocalDate date);
//    List<ExerciseTracker> findByExerciseIdAndDate(Long exerciseId, LocalDate date);
//}