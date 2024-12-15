package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TrueFalseQuestion")
public class TrueFalseQuestion extends Question {

    @Column(nullable = false)
    private String correctAnswer; // "true" or "false"

    // Getters and Setters
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
