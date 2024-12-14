package com.example.lms.assessments.model;

import jakarta.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "QuestionBank")
public class QuestionBank {
    @EmbeddedId
    private QuestionBankKey id;

    @ManyToOne
    @MapsId("quizId")
    @JoinColumns({
            @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id"),
            @JoinColumn(name = "assessment_id", referencedColumnName = "assessment_id")
    })
    private Quiz quiz;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    public QuestionBank() {}

    public QuestionBank(QuestionBankKey id, Quiz quiz, Question question) {
        this.id = id;
        this.quiz = quiz;
        this.question = question;
    }

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
