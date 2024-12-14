package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MCQQuestion")
@DiscriminatorValue("mcq")
public class MCQQuestion extends Question {
    @Column(nullable = false)
    private String correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
