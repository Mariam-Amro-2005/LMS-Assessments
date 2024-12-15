package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.MCQQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MCQQuestionRepository extends JpaRepository<MCQQuestion, Integer> {}
