package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.model.QuizId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, QuizId> {
}
