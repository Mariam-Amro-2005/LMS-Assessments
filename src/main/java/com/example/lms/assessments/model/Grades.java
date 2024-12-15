package com.example.lms.assessments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gradeId;

    @Column(nullable = false)
    private Integer userId;

    @Embedded
    private QuizId quizKey; // Nullable for assignments

    @Embedded
    private AssignmentKey assignmentKey; // Nullable for quizzes

    @Column(nullable = false)
    private Float grade;

    @Column(length = 500)
    private String feedback; // Optional feedback for manual grading

    // Getters and Setters
    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public QuizId getQuizKey() {
        return quizKey;
    }

    public void setQuizKey(QuizId quizKey) {
        this.quizKey = quizKey;
    }

    public AssignmentKey getAssignmentKey() {
        return assignmentKey;
    }

    public void setAssignmentKey(AssignmentKey assignmentKey) {
        this.assignmentKey = assignmentKey;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
