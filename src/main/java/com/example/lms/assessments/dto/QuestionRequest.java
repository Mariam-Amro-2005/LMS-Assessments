package com.example.lms.assessments.dto;

import com.example.lms.assessments.model.QuizId;

import java.util.List;

public class QuestionRequest {

    private String text;
    private String type;
    private List<String> options;
    private String correctAnswer;
    private QuizId quizId; // Updated to use QuizId instead of Integer

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public QuizId getQuizId() {
        return quizId;
    }

    public void setQuizId(QuizId quizId) {
        this.quizId = quizId;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType(){
        return type;
    }
}
