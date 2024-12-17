package com.example.lms.assessments.controller;

import com.example.lms.assessments.dto.AssignmentRequest;
import com.example.lms.assessments.dto.QuizRequest;
import com.example.lms.assessments.model.Assignment;
import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    // Create Assignment
    @PostMapping("/assignments")
    public ResponseEntity<String> createAssignment(@RequestBody AssignmentRequest assignmentRequest) {
        String response = assessmentService.createAssignment(assignmentRequest);
        return ResponseEntity.ok(response);
    }

    // Create Quiz
    @PostMapping("/quizzes")
    public ResponseEntity<String> createQuiz(@RequestBody QuizRequest quizRequest) {
        String response = assessmentService.createQuiz(quizRequest);
        return ResponseEntity.ok(response);
    }

    // Get Assignment by ID
    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable int id) {
        Optional<Assignment> assignment = assessmentService.getAssignmentById(id);
        return assignment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get Quiz by ID
    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable int id) {
        Optional<Quiz> quiz = assessmentService.getQuizById(id);
        return quiz.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
