package com.example.lms.assessments.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
//@Table(name = "MCQQuestion")
@PrimaryKeyJoinColumn(name = "question_id")
public class MCQQuestion extends Question {

    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @ElementCollection
    @CollectionTable(name = "mcq_options", joinColumns = @JoinColumn(name = "question_id"))
    private List<String> options;

    // Getters and Setters
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}

