package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q JOIN q.quizzes quiz WHERE quiz.assessmentId = :quizId")
    List<Question> findByQuizId(@Param("quizId") Integer quizId);
}
