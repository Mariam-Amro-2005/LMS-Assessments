package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
    @Query
    Optional<Assessment> findById(Long assessment_id);
}
