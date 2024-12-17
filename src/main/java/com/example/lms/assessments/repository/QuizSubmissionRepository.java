package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Integer> {
    Optional<QuizSubmission> findByQuiz_QuizIdAndUserId(Integer quizId, Integer userId);
}
