package com.example.lms.assessments.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Quiz")
public class Quiz {
    @EmbeddedId
    private QuizId id;

    @Column(name = "time_in_minutes")
    private int timeInMinutes;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionBank> questionBanks;

    // Constructors
    public Quiz() {}

    public Quiz(QuizId id, int timeInMinutes) {
        this.id = id;
        this.timeInMinutes = timeInMinutes;
    }

    // Getters and Setters
    public QuizId getId() {
        return id;
    }

    public void setId(QuizId id) {
        this.id = id;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public List<QuestionBank> getQuestionBanks() {
        return questionBanks;
    }

    public void setQuestionBanks(List<QuestionBank> questionBanks) {
        this.questionBanks = questionBanks;
    }
}
