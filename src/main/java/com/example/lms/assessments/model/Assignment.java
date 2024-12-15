package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
public class Assignment {

    @EmbeddedId
    private AssignmentKey assignmentKey;

    @Column(nullable = false)
    private String question;

    @ManyToOne
    @JoinColumn(name = "assessment_id", insertable = false, updatable = false)
    private Assessment assessment;

    // Other fields, getters, and setters...

    public AssignmentKey getAssignmentKey() {
        return assignmentKey;
    }

    public void setAssignmentKey(AssignmentKey assignmentKey) {
        this.assignmentKey = assignmentKey;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }
}
