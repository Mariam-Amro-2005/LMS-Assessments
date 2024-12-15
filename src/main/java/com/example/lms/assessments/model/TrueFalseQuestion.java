package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TrueFalseQuestion")
@DiscriminatorValue("true_false")
public class TrueFalseQuestion extends Question {
    @Column(nullable = false)
    private String correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
