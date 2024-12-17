package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.QuestionBank;
import com.example.lms.assessments.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {
    //List<QuestionBank> findByIdQuizKey(Integer quizKey);

    List<QuestionBank> findByQuizId(Integer quizId);
    // Find all QuestionBanks containing a specific Quiz
    List<QuestionBank> findByQuizzes(Quiz quiz);
}

