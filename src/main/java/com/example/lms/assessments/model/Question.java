package com.example.lms.assessments.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Question")
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @ManyToMany
    @JoinTable(
            name = "Question_QuestionBank",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "question_bank_id")
    )
    private List<QuestionBank> questionBanks;

    @ManyToMany
    private List<Quiz> quizzes;

    private String text;

    // Getters and setters
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<QuestionBank> getQuestionBanks() {
        return questionBanks;
    }

    public void setQuestionBanks(List<QuestionBank> questionBanks) {
        this.questionBanks = questionBanks;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    // Methods to add question banks and quizzes
    public void addQuestionBank(QuestionBank questionBank) {
        this.questionBanks.add(questionBank);
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public String getType() {
        if (this instanceof MCQQuestion) return "mcq";
        if (this instanceof TrueFalseQuestion) return "true_false";
        if (this instanceof ShortAnswerQuestion) return "short_answer";
        return "unknown";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
