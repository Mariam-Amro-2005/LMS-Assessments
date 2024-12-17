package com.example.lms.assessments.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.List;

@Entity
//@Table(name = "Quiz")
@PrimaryKeyJoinColumn(name = "assessment_id")
public class Quiz extends Assessment {

    @Column(name = "time_in_minutes", nullable = false)
    private int timeInMinutes;

    @ManyToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "assessment_id", referencedColumnName = "assessmentId")
    private List<Question> questions;

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
}
