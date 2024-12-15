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
        QuizId quizKey = request.getQuizId();
        Optional<Quiz> quizOpt = quizRepository.findById(quizKey);
        if (quizOpt.isEmpty()) {
            throw new IllegalArgumentException("Quiz not found for key: " + quizKey);
        }

        Quiz quiz = quizOpt.get();
        Question question = saveQuestionByType(request); // Save to specific type table

        // Add to QuestionBank
        QuestionBank questionBank = new QuestionBank();
        questionBank.setId(new QuestionBankKey(quizKey, question.getQuestionId()));
        questionBank.setQuiz(quiz);
        questionBank.setQuestion(question);
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

    public List<QuestionResponse> getQuestionsByQuizId(QuizId quizId) {
        // Fetch all QuestionBank entries for the quiz
        List<QuestionBank> questionBanks = questionBankRepository.findByIdQuizKey(quizId);

        // Map to QuestionResponse DTOs
        return questionBanks.stream()
                .map(questionBank -> mapToQuestionResponse(questionBank.getQuestion()))
                .collect(Collectors.toList());
    }

    private QuestionResponse mapToQuestionResponse(Question question) {
        QuestionResponse response = new QuestionResponse();
        response.setQuestionId(question.getQuestionId());
        response.setText(question.getText());
        response.setType(question.getType());

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

