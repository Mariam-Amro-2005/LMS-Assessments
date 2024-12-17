package com.example.lms.assessments.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "QuestionBank")
public class QuestionBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionBankId;

    @ManyToMany(mappedBy = "questionBanks")
    private List<Question> questions;

    @ManyToMany(mappedBy = "questionBanks")
    private List<Quiz> quizzes;

    // Getters and setters
    public Integer getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(Integer questionBankId) {
        this.questionBankId = questionBankId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    // Methods to add questions and quizzes
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }
}
