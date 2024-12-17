package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {
    // Directly operate on QuestionBank; no need for quiz-related methods
}
