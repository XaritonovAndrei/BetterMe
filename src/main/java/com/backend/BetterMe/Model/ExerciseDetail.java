//package com.backend.BetterMe.Model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import java.time.LocalDate;
//
///**
// * Exercises details table (reps, sets, weight and date, when exercise was performed).
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "exercise_details")
//public class ExerciseDetail {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    /**
//     * Foreign key to Exercise.id
//     */
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "exercise_id", nullable = false)
//    private Exercise exercise;
//
//    @Column(name = "repetitions", nullable = false)
//    private int repetitions;
//
//    @Column(name = "sets", nullable = false)
//    private int sets;
//
//    @Column(name = "weight", nullable = false)
//    private double weight;
//
////    @Column(name = "date", nullable = false)
////    private LocalDate date;
//}