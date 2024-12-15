package com.example.lms.assessments.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "QuizSubmission")
public class QuizSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;

    @Embedded
    private QuizId quizKey;

    @Column(nullable = false)
    private Integer userId;

    @ElementCollection
    @CollectionTable(name = "quiz_submission_answers", joinColumns = @JoinColumn(name = "submission_id"))
    @MapKeyColumn(name = "question_id")
    @Column(name = "student_answer")
    private Map<Integer, String> studentAnswers; // Question ID mapped to student answers

    // Getters and Setters
    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public QuizId getQuizKey() {
        return quizKey;
    }

    public void setQuizKey(QuizId quizKey) {
        this.quizKey = quizKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Map<Integer, String> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(Map<Integer, String> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }
}
