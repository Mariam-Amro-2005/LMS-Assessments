package com.example.lms.assessments.controller;

import com.example.lms.assessments.model.Grades;
import com.example.lms.assessments.service.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/grading")
public class GradingController {

    private final GradingService gradingService;

    @Autowired
    public GradingController(GradingService gradingService) {
        this.gradingService = gradingService;
    }

    // Automatic grading for quizzes
    @PostMapping("/quiz")
    public ResponseEntity<String> autoGradeQuiz(
            @RequestParam Integer userId,
            @RequestParam Integer quizId) {
        try {
            String result = gradingService.autoGradeQuiz(userId, quizId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error grading quiz: " + e.getMessage());
        }
    }

    // Manual grading for assignments
    @PostMapping("/assignment")
    public ResponseEntity<String> manualGradeAssignment(
            @RequestParam Integer userId,
            @RequestParam Integer assignmentId,
            @RequestParam Float grade,
            @RequestParam(required = false) String feedback) {
        try {
            String result = gradingService.manualGradeAssignment(userId, assignmentId, grade, feedback);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error grading assignment: " + e.getMessage());
        }
    }

    // Fetch grade for a specific quiz
    @GetMapping("/quiz")
    public ResponseEntity<Grades> getGradeByQuiz(
            @RequestParam Integer userId,
            @RequestParam Integer quizId) {
        Optional<Grades> gradeOpt = gradingService.getGradeByUserAndAssessment(userId, quizId);
        return gradeOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Fetch grade for a specific assignment
    @GetMapping("/assignment")
    public ResponseEntity<Grades> getGradeByAssignment(
            @RequestParam Integer userId,
            @RequestParam Integer assignmentId) {
        Optional<Grades> gradeOpt = gradingService.getGradeByUserAndAssessment(userId, assignmentId);
        return gradeOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
