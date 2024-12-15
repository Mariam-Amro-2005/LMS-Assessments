package com.example.lms.assessments.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Assessment {
    @Id
    @SequenceGenerator(
            name = "assessment_sequence",
            sequenceName = "assessment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "assessment_sequence"
    )
    Integer assessmentId;
    String type;
    LocalDate created_date;
    LocalDate due_date;
    private int courseId;
    float max_score;
    @Column(length = 500)
    private String feedback;

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public LocalDate getDueDate() {
        return due_date;
    }

    public void setDueDate(LocalDate dueDate) {
        this.due_date = dueDate;
    }

    public float getMaxScore() {
        return max_score;
    }

    public void setMaxScore(float maxScore) {
        this.max_score = maxScore;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
