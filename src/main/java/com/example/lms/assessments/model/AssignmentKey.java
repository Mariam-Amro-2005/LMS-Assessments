package com.example.lms.assessments.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AssignmentKey implements Serializable {
    private int assignmentId;
    private Integer assessmentId;

    public AssignmentKey() {}

    public AssignmentKey(int assignmentId, int assessmentId) {
        this.assignmentId = assignmentId;
        this.assessmentId = assessmentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentKey that = (AssignmentKey) o;
        return assignmentId == that.assignmentId && Objects.equals(assessmentId, that.assessmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, assessmentId);
    }
}
