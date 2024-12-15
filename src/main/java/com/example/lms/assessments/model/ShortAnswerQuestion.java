package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ShortAnswerQuestion")
public class ShortAnswerQuestion extends Question {

    @Column(nullable = false)
    private String correctAnswer;

    // Getters and Setters
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

