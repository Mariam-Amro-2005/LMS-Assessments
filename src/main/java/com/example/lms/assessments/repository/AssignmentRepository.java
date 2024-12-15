package com.example.lms.assessments.repository;

import com.example.lms.assessments.model.Assignment;
import com.example.lms.assessments.model.AssignmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, AssignmentKey> {
}
