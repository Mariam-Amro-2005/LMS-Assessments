package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TrueFalseQuestion")
@DiscriminatorValue("true_false")
public class TrueFalseQuestion extends Question {
    @Column(nullable = false)
    private Boolean correctAnswer;

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
