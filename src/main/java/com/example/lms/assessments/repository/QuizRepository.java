package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
