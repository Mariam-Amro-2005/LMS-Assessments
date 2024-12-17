package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "assessment_id")
public class Assignment extends Assessment {
    @Column(name = "question", nullable = false)
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
