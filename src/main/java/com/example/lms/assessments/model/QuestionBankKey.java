package com.example.lms.assessments.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuestionBankKey implements Serializable {

    private int quizId;
    private int assessmentId;
    private int questionId;

    // Default constructor
    public QuestionBankKey() {}

    // Parameterized constructor
    public QuestionBankKey(int quizId, int assessmentId, int questionId) {
        this.quizId = quizId;
        this.assessmentId = assessmentId;
        this.questionId = questionId;
    }

    // Getters and Setters
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionBankKey that = (QuestionBankKey) o;
        return quizId == that.quizId &&
                assessmentId == that.assessmentId &&
                questionId == that.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, assessmentId, questionId);
    }
}
