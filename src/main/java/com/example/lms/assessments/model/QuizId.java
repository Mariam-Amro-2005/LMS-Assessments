package com.example.lms.assessments.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuizId implements Serializable {
    private int quizId;
    private Long assessmentId;

    // Constructors
    public QuizId() {}

    public QuizId(int quizId, Long assessmentId) {
        this.quizId = quizId;
        this.assessmentId = assessmentId;
    }

    // Getters and Setters
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    // hashCode and equals (required for composite key)
    @Override
    public int hashCode() {
        return Objects.hash(quizId, assessmentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuizId that = (QuizId) obj;
        return quizId == that.quizId &&
                Objects.equals(assessmentId, that.assessmentId);
    }
}
