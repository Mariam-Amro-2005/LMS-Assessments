package com.example.lms.assessments.service;

import com.example.lms.assessments.model.AssignmentSubmission;
import com.example.lms.assessments.model.AssignmentKey;
import com.example.lms.assessments.model.QuizSubmission;
import com.example.lms.assessments.model.QuizId;
import com.example.lms.assessments.repository.AssignmentSubmissionRepository;
import com.example.lms.assessments.repository.QuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionService {

    private final AssignmentSubmissionRepository assignmentSubmissionRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;

    @Autowired
    public SubmissionService(
            AssignmentSubmissionRepository assignmentSubmissionRepository,
            QuizSubmissionRepository quizSubmissionRepository) {
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
    }

    // Save Assignment Submission
    public void saveAssignmentSubmission(AssignmentSubmission submission) {
        assignmentSubmissionRepository.save(submission);
    }

    // Save Quiz Submission
    public void saveQuizSubmission(QuizSubmission submission) {
        quizSubmissionRepository.save(submission);
    }

    // Get Assignment Submission
    public Optional<AssignmentSubmission> getAssignmentSubmission(AssignmentKey key, Integer userId) {
        return assignmentSubmissionRepository.findByAssignmentKeyAndUserId(key, userId);
    }

    // Get Quiz Submission
    public Optional<QuizSubmission> getQuizSubmission(QuizId key, Integer userId) {
        return quizSubmissionRepository.findByQuizKeyAndUserId(key, userId);
    }
}
