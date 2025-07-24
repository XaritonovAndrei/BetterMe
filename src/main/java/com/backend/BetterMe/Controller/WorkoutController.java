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

@Controller
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public String showWorkoutPage(
            Model model,
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        LocalDate targetDate = (date != null) ? date : LocalDate.now();
        List<WorkoutHistory> entries = workoutService.getAllWorkoutByDate(targetDate);

        // model attributes
        model.addAttribute("entries", entries);
        model.addAttribute("selectedDate", targetDate);
        List<Exercise> exercises = workoutService.getAllExercises();
        model.addAttribute("exercises", exercises);
        model.addAttribute("newExercise", new Exercise());

        return "workout";
    }

    @PostMapping("/addExercise")
    public String addExercise(@ModelAttribute("newExercise") Exercise exercise) {
        workoutService.addExercise(exercise);
        return "redirect:/workout";
    }

    @DeleteMapping("/deleteExercise/{id}")
    public String deleteExercise(@PathVariable("id") Long id) {
        workoutService.deleteExercise(id);
        return "redirect:/workout";
    }

    @PostMapping("/recordWorkout")
    @ResponseBody
    public WorkoutHistory recordWorkout(
            // date recording in service layer in recordWorkout method
            @RequestParam Long exerciseId,
            @RequestParam int reps,
            @RequestParam int sets,
            @RequestParam double weight
    ) {
        return workoutService.recordWorkout(exerciseId, reps, sets, weight);
    }

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


    @GetMapping("/workoutByDate")
    @ResponseBody
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