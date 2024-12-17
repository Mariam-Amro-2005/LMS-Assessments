package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Integer> {
    //Optional<QuizSubmission> findByQuiz_QuizIdAndUserId(Integer quizId, Integer userId);
    @Query("SELECT qs FROM QuizSubmission qs WHERE qs.quiz.assessmentId = :quizId AND qs.userId = :userId")
    Optional<QuizSubmission> findByQuiz_QuizIdAndUserId(@Param("quizId") Integer quizId, @Param("userId") Integer userId);
}
