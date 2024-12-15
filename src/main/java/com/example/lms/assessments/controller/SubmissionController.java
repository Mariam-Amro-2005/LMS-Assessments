package com.example.lms.assessments.controller;

import com.example.lms.assessments.model.AssignmentSubmission;
import com.example.lms.assessments.model.AssignmentKey;
import com.example.lms.assessments.model.QuizSubmission;
import com.example.lms.assessments.model.QuizId;
import com.example.lms.assessments.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/assignment")
    public ResponseEntity<String> uploadAssignment(
            @RequestParam Integer userId,
            @RequestParam Integer assessmentId,
            @RequestParam Integer assignmentId,
            @RequestParam MultipartFile file) {
        try {
            AssignmentSubmission submission = new AssignmentSubmission();
            submission.setUserId(userId);
            submission.setAssignmentKey(new AssignmentKey(assessmentId, assignmentId));
            submission.setSubmittedFile(file.getBytes());
            submissionService.saveAssignmentSubmission(submission);
            return ResponseEntity.ok("Assignment submission saved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> uploadQuiz(
            @RequestParam Integer userId,
            @RequestParam Integer assessmentId,
            @RequestParam Integer quizId,
            @RequestParam Map<Integer, String> studentAnswers) {
        try {
            QuizSubmission submission = new QuizSubmission();
            submission.setUserId(userId);
            submission.setQuizKey(new QuizId(assessmentId, quizId));
            submission.setStudentAnswers(studentAnswers);
            submissionService.saveQuizSubmission(submission);
            return ResponseEntity.ok("Quiz submission saved.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
