package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Integer> {
    Optional<AssignmentSubmission> findByAssignment_AssignmentIdAndUserId(Integer assignmentId, Integer userId);
}
