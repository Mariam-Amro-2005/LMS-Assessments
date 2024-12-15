package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByQuiz_QuizId(Integer quizId);
}
