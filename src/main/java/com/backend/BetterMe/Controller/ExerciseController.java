//package com.backend.BetterMe.Controller;
//
//
//import com.backend.BetterMe.Model.Exercise;
//import com.backend.BetterMe.Service.ExerciseService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/exercises")
//@RequiredArgsConstructor
//public class ExerciseController {
//
//    private final ExerciseService exerciseService;
//
//    // ! @RequiredArgsConstructor conflict?
////    public ExerciseController(ExerciseService exerciseService) {
////        this.exerciseService = exerciseService;
////    }
//
//    @GetMapping
//    public String showExercises(Model model) {
//        model.addAttribute("exercises", exerciseService.getAllExercises());
//        model.addAttribute("newExercise", new Exercise());
//        model.addAttribute("todayExercises", exerciseService.getTodayExercises());
//        model.addAttribute("todayCompletion", exerciseService.getTodayCompletion());
//        return "exercises";
//    }
//
//    @PostMapping
//    public String addExercise(@ModelAttribute("newExercise") Exercise exercise) {
//        exerciseService.addExercise(exercise);
//        return "redirect:/exercises";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteExercise(@PathVariable("id") Long id) {
//        exerciseService.deleteExercise(id);
//        return "redirect:/exercises";
//    }
//
//    @PostMapping("/{id}/addToToday")
//    public String addToToday(@PathVariable("id") Long id) {
//        exerciseService.addToTodayWorkout(id);
//        return "redirect:/exercises";
//    }
//
//    @DeleteMapping("/today/{exerciseId}")
//    public String deleteTodayExercise(@PathVariable("exerciseId") Long exerciseId) {
//        exerciseService.deleteTodayEntryByExerciseId(exerciseId);
//        return "redirect:/exercises";
//    }
//}
