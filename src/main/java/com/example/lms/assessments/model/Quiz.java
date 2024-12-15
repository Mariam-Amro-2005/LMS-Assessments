package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
public class Quiz {

    @EmbeddedId
    private QuizId quizId;

    @Column(nullable = false)
    private int timeInMinutes;

    @ManyToOne
    @JoinColumn(name = "assessment_id", insertable = false, updatable = false)
    private Assessment assessment;

    // Other fields, getters, and setters...

    public QuizId getQuizId() {
        return quizId;
    }

    public void setQuizId(QuizId quizId) {
        this.quizId = quizId;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }
}
