package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.ShortAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShortAnswerQuestionRepository extends JpaRepository<ShortAnswerQuestion, Integer> {}