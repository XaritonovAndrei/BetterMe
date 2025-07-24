package com.backend.BetterMe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "workout_entries")
public class WorkoutHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "date_performed", nullable = false)
    private LocalDate datePerformed;

    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "sets", nullable = false)
    private int sets;

    @Column(name = "weight", nullable = false)
    private double weight;
}