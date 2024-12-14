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
    Long assessmentId;
    String type;
    LocalDate created_date;
    LocalDate due_date;
    private int courseId;
    float max_score;

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

}
