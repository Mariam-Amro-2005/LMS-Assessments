package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Assignment")
public class Assignment {
    @EmbeddedId
    private AssignmentKey id;

    @ManyToOne
    @MapsId("assessmentId") // Maps the assessmentId in AssignmentKey to the Assessment entity
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @Column(name = "question", nullable = false)
    private String question;

    public Assignment() {}

    public AssignmentKey getId() {
        return id;
    }

    public void setId(AssignmentKey id) {
        this.id = id;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
