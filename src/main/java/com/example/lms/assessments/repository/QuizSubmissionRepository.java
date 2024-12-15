package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.QuizSubmission;
import com.example.lms.assessments.model.QuizId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Integer> {

    Optional<QuizSubmission> findByQuizKeyAndUserId(QuizId quizKey, Integer userId);
}
