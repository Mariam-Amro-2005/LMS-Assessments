package com.example.lms.assessments.model;

import jakarta.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "QuestionBank")
public class QuestionBank {

    @EmbeddedId
    private QuestionBankKey id;

    @ManyToOne
    @MapsId("quizKey")
    @JoinColumns({
            @JoinColumn(name = "assessment_id", referencedColumnName = "assessmentId"),
            @JoinColumn(name = "quiz_id", referencedColumnName = "quizId")
    })
    private Quiz quiz;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

    // Getters and Setters
    public QuestionBankKey getId() {
        return id;
    }

    public void setId(QuestionBankKey id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
