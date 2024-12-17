package com.example.lms.assessments.controller;

import com.example.lms.assessments.model.AssignmentSubmission;
import com.example.lms.assessments.model.QuizSubmission;
import com.example.lms.assessments.model.Assignment;
import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    // Upload Assignment Submission
    @PostMapping("/assignment")
    public ResponseEntity<String> uploadAssignment(
            @RequestParam Integer userId,
            @RequestParam Integer assignmentId,
            @RequestParam MultipartFile file) {
        try {
            AssignmentSubmission submission = new AssignmentSubmission();
            submission.setUserId(userId);
            submission.setSubmittedFile(file.getBytes());
            submissionService.saveAssignmentSubmission(assignmentId, submission);
            return ResponseEntity.ok("Assignment submission saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Upload Quiz Submission
    @PostMapping("/quiz")
    public ResponseEntity<String> uploadQuiz(
            @RequestParam Integer userId,
            @RequestParam Integer quizId,
            @RequestParam Map<Integer, String> studentAnswers) {
        try {
            QuizSubmission submission = new QuizSubmission();
            submission.setUserId(userId);
            submission.setStudentAnswers(studentAnswers);
            submissionService.saveQuizSubmission(quizId, submission);
            return ResponseEntity.ok("Quiz submission saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
