package com.example.lms.assessments.service;

import com.example.lms.assessments.dto.QuestionRequest;
import com.example.lms.assessments.dto.QuestionResponse;
import com.example.lms.assessments.model.Question;
import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.repository.QuestionRepository;
import com.example.lms.assessments.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public String createQuestion(QuestionRequest request) {
        Optional<Quiz> quizOpt = quizRepository.findById(request.getQuizId()); // Updated to use QuizId
        if (quizOpt.isEmpty()) {
            throw new IllegalArgumentException("Quiz not found for ID: " + request.getQuizId());
        }

        Question question = mapToQuestion(request, quizOpt.get());
        questionRepository.save(question);
        return "Question created successfully.";
    }


    public List<QuestionResponse> getQuestionsByQuizId(Integer quizId) {
        return questionRepository.findByQuiz_QuizId(quizId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private Question mapToQuestion(QuestionRequest request, Quiz quiz) {
        Question question = new Question();
        question.setText(request.getText());
        question.setOptions(request.getOptions());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setQuiz(quiz);
        return question;
    }

    private QuestionResponse mapToResponse(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setQuestionId(question.getQuestionId());
        response.setText(question.getText());
        response.setOptions(question.getOptions());
        response.setCorrectAnswer(question.getCorrectAnswer());
        return response;
    }
}
