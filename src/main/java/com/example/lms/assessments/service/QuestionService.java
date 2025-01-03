package com.example.lms.assessments.service;

import com.example.lms.assessments.dto.QuestionRequest;
import com.example.lms.assessments.dto.QuestionResponse;
import com.example.lms.assessments.model.*;
import com.example.lms.assessments.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MCQQuestionRepository mcqQuestionRepository;
    private final TrueFalseQuestionRepository trueFalseQuestionRepository;
    private final ShortAnswerQuestionRepository shortAnswerQuestionRepository;
    private final QuestionBankRepository questionBankRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuestionService(
            QuestionRepository questionRepository,
            MCQQuestionRepository mcqQuestionRepository,
            TrueFalseQuestionRepository trueFalseQuestionRepository,
            ShortAnswerQuestionRepository shortAnswerQuestionRepository,
            QuestionBankRepository questionBankRepository,
            QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.mcqQuestionRepository = mcqQuestionRepository;
        this.trueFalseQuestionRepository = trueFalseQuestionRepository;
        this.shortAnswerQuestionRepository = shortAnswerQuestionRepository;
        this.questionBankRepository = questionBankRepository;
        this.quizRepository = quizRepository;
    }

    public String createQuestion(QuestionRequest request) {
        // Find the associated quiz
        Integer quizId = request.getQuizId();
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        // Save the question based on its type
        Question question = saveQuestionByType(request);

        // Add the question to the quiz and the question bank
        quiz.addQuestion(question);
        question.addQuiz(quiz);

        // Create and save the QuestionBank entry
        QuestionBank questionBank = new QuestionBank();
        questionBank.addQuestion(question);
        //questionBank.addQuiz(quiz);

        questionBankRepository.save(questionBank);

        return "Question created and added to QuestionBank successfully.";
    }

    private Question saveQuestionByType(QuestionRequest request) {
        switch (request.getType().toLowerCase()) {
            case "mcq":
                MCQQuestion mcq = new MCQQuestion();
                mcq.setText(request.getText());
                mcq.setOptions(request.getOptions());
                mcq.setCorrectAnswer(request.getCorrectAnswer());
                return mcqQuestionRepository.save(mcq);

            case "true_false":
                TrueFalseQuestion tf = new TrueFalseQuestion();
                tf.setText(request.getText());
                tf.setCorrectAnswer(request.getCorrectAnswer());
                return trueFalseQuestionRepository.save(tf);

            case "short_answer":
                ShortAnswerQuestion sa = new ShortAnswerQuestion();
                sa.setText(request.getText());
                sa.setCorrectAnswer(request.getCorrectAnswer());
                return shortAnswerQuestionRepository.save(sa);

            default:
                throw new IllegalArgumentException("Invalid question type: " + request.getType());
        }
    }

    public List<QuestionResponse> getQuestionsByQuizId(Integer quizId) {
        // Fetch the Quiz object
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with ID: " + quizId));

        // Map all Questions in the Quiz to QuestionResponse
        return quiz.getQuestions().stream()
                .map(this::mapToQuestionResponse)
                .collect(Collectors.toList());
    }



    private QuestionResponse mapToQuestionResponse(Question question) {
        // Create a new response object
        QuestionResponse response = new QuestionResponse();
        response.setQuestionId(question.getQuestionId());
        response.setText(question.getText());
        response.setType(question.getType());

        // Handle specific question types
        if (question instanceof MCQQuestion) {
            MCQQuestion mcq = (MCQQuestion) question;
            response.setOptions(mcq.getOptions());
            response.setCorrectAnswer(mcq.getCorrectAnswer());
        } else if (question instanceof TrueFalseQuestion) {
            TrueFalseQuestion tf = (TrueFalseQuestion) question;
            response.setCorrectAnswer(tf.getCorrectAnswer());
        } else if (question instanceof ShortAnswerQuestion) {
            ShortAnswerQuestion sa = (ShortAnswerQuestion) question;
            response.setCorrectAnswer(sa.getCorrectAnswer());
        }

        return response;
    }

}
