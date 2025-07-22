package com.backend.BetterMe.Repository;

import com.backend.BetterMe.Model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
}

