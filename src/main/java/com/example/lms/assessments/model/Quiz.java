package com.example.lms.assessments.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "assessment_id")
public class Quiz extends Assessment {

    @Column(name = "time_in_minutes", nullable = false)
    private int timeInMinutes;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    // Getters and setters
    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // Method to add questions
    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
