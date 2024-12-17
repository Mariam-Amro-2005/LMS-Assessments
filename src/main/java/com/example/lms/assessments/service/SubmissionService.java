package com.example.lms.assessments.service;

import com.example.lms.assessments.model.AssignmentSubmission;
import com.example.lms.assessments.model.QuizSubmission;
import com.example.lms.assessments.model.Assignment;
import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.repository.AssignmentSubmissionRepository;
import com.example.lms.assessments.repository.QuizSubmissionRepository;
import com.example.lms.assessments.repository.AssignmentRepository;
import com.example.lms.assessments.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionService {

    private final AssignmentSubmissionRepository assignmentSubmissionRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public SubmissionService(
            AssignmentSubmissionRepository assignmentSubmissionRepository,
            QuizSubmissionRepository quizSubmissionRepository,
            AssignmentRepository assignmentRepository,
            QuizRepository quizRepository) {
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.quizRepository = quizRepository;
    }

    // Save Assignment Submission
    public void saveAssignmentSubmission(Integer assignmentId, AssignmentSubmission submission) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found with ID: " + assignmentId));
        submission.setAssignment(assignment);
        assignmentSubmissionRepository.save(submission);
    }

    // Save Quiz Submission
    public void saveQuizSubmission(Integer quizId, QuizSubmission submission) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));
        submission.setQuiz(quiz);
        quizSubmissionRepository.save(submission);
    }

    // Get Assignment Submission by User ID and Assignment
    public Optional<AssignmentSubmission> getAssignmentSubmission(Integer assignmentId, Integer userId) {
        return assignmentSubmissionRepository.findByAssignment_AssignmentIdAndUserId(assignmentId, userId);
    }

    // Get Quiz Submission by User ID and Quiz
    public Optional<QuizSubmission> getQuizSubmission(Integer quizId, Integer userId) {
        return quizSubmissionRepository.findByQuiz_QuizIdAndUserId(quizId, userId);
    }
}
