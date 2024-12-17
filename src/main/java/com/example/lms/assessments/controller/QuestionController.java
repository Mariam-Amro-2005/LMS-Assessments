package com.example.lms.assessments.controller;

import com.example.lms.assessments.dto.QuestionRequest;
import com.example.lms.assessments.dto.QuestionResponse;
import com.example.lms.assessments.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessments/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionRequest request) {
        return ResponseEntity.ok(questionService.createQuestion(request));
    }

    // Specific endpoints for question types
    @PostMapping("/mcq")
    public ResponseEntity<String> createMCQ(@RequestBody QuestionRequest request) {
        try {
            request.setType("mcq");
            String response = questionService.createQuestion(request);
            return ResponseEntity.ok("MCQ created successfully. " + response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/true-false")
    public ResponseEntity<String> createTrueFalse(@RequestBody QuestionRequest request) {
        try {
            request.setType("true_false");
            String response = questionService.createQuestion(request);
            return ResponseEntity.ok("True/False question created successfully. " + response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/short-answer")
    public ResponseEntity<String> createShortAnswer(@RequestBody QuestionRequest request) {
        request.setType("short_answer");
        return ResponseEntity.ok(questionService.createQuestion(request));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByQuizId(@PathVariable Integer quizId) {
        // Call the service method to get the questions for the given quizId
        List<QuestionResponse> questions = questionService.getQuestionsByQuizId(quizId);

        // Return the response with the list of questions
        return ResponseEntity.ok(questions);
    }

}
