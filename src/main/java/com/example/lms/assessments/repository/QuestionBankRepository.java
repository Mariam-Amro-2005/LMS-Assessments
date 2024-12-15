package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.QuestionBank;
import com.example.lms.assessments.model.QuestionBankKey;
import com.example.lms.assessments.model.QuizId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, QuestionBankKey> {
    List<QuestionBank> findByIdQuizKey(QuizId quizKey);
}

