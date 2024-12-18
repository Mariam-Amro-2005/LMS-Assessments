package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Integer> {

    @Query("SELECT a FROM AssignmentSubmission a WHERE a.assignment.assessmentId = :assignmentId AND a.userId = :userId")
    Optional<AssignmentSubmission> findByAssignment_AssignmentIdAndUserId(@Param("assignmentId") Integer assignmentId,
                                                                          @Param("userId") Integer userId);
}
