package com.example.lms.assessments.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id")
    private Integer assessmentId;

    private String type; // "quiz" or "assignment"

    @Temporal(TemporalType.DATE)
    LocalDate created_date;
    @Temporal(TemporalType.DATE)
    private LocalDate due_date;

    private int courseId;

    private float max_score;


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

    public void setCreated_date(LocalDate created_date){
        this.created_date = created_date;
    }

    public void setType(String type){
        this.type = type;
    }

}
