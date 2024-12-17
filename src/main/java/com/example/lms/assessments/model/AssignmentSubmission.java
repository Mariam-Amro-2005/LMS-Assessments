package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "AssignmentSubmission")
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Column(nullable = false)
    private Integer userId;

    @Lob
    private byte[] submittedFile;

    // Getters and Setters
    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getSubmittedFile() {
        return submittedFile;
    }

    public void setSubmittedFile(byte[] submittedFile) {
        this.submittedFile = submittedFile;
    }
}
