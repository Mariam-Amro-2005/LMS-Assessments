package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {
    //List<QuestionBank> findByIdQuizKey(Integer quizKey);

    List<QuestionBank> findByQuizId(Integer quizId);
}

