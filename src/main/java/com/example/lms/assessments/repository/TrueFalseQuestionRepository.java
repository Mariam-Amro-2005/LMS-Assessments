package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.TrueFalseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrueFalseQuestionRepository extends JpaRepository<TrueFalseQuestion, Integer> {}