package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Grades;
import com.example.lms.assessments.model.AssignmentKey;
import com.example.lms.assessments.model.QuizId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradesRepository extends JpaRepository<Grades, Integer> {

    Optional<Grades> findByUserIdAndQuizKey(Integer userId, QuizId quizKey);

    Optional<Grades> findByUserIdAndAssignmentKey(Integer userId, AssignmentKey assignmentKey);
}
