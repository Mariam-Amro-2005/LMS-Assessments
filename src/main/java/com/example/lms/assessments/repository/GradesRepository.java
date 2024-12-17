package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Grades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradesRepository extends JpaRepository<Grades, Integer> {
    Optional<Grades> findByUserIdAndAssessment_AssessmentId(Integer userId, Integer assessmentId);
}
