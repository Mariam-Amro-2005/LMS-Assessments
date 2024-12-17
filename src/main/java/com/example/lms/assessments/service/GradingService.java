package com.example.lms.assessments.service;

import com.example.lms.assessments.model.*;
import com.example.lms.assessments.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GradingService {

    private final GradesRepository gradesRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final AssignmentSubmissionRepository assignmentSubmissionRepository;
    private final AssessmentRepository assessmentRepository;
    private final QuestionBankRepository questionBankRepository;

    @Autowired
    public GradingService(
            GradesRepository gradesRepository,
            QuizSubmissionRepository quizSubmissionRepository,
            AssignmentSubmissionRepository assignmentSubmissionRepository,
            AssessmentRepository assessmentRepository,
            QuestionBankRepository questionBankRepository) {
        this.gradesRepository = gradesRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
        this.assessmentRepository = assessmentRepository;
        this.questionBankRepository = questionBankRepository;
    }

    // Automatic grading for quizzes
    public String autoGradeQuiz(Integer userId, Integer quizId) {
        // Fetch QuizSubmission
        Optional<QuizSubmission> submissionOpt = quizSubmissionRepository.findByQuiz_QuizIdAndUserId(quizId, userId);
        if (submissionOpt.isEmpty()) {
            throw new IllegalArgumentException("Quiz submission not found.");
        }

        QuizSubmission submission = submissionOpt.get();

        // Calculate grade
        float calculatedGrade = calculateQuizGrade(submission);

        // Save grade
        Grades grade = new Grades();
        grade.setUserId(userId);
        grade.setAssessment(submission.getQuiz());
        grade.setGrade(calculatedGrade);

        gradesRepository.save(grade);
        return "Quiz graded successfully. Grade: " + calculatedGrade;
    }

    // Manual grading for assignments
    public String manualGradeAssignment(Integer userId, Integer assignmentId, Float gradeValue, String feedback) {
        // Fetch AssignmentSubmission
        Optional<AssignmentSubmission> submissionOpt = assignmentSubmissionRepository.findByAssignment_AssignmentIdAndUserId(assignmentId, userId);
        if (submissionOpt.isEmpty()) {
            throw new IllegalArgumentException("Assignment submission not found.");
        }

        AssignmentSubmission submission = submissionOpt.get();

        // Save grade
        Grades grade = new Grades();
        grade.setUserId(userId);
        grade.setAssessment(submission.getAssignment());
        grade.setGrade(gradeValue);
        grade.setFeedback(feedback);

        gradesRepository.save(grade);
        return "Assignment graded successfully. Grade: " + gradeValue;
    }

    // Fetch grade by user and assessment
    public Optional<Grades> getGradeByUserAndAssessment(Integer userId, Integer assessmentId) {
        return gradesRepository.findByUserIdAndAssessment_AssessmentId(userId, assessmentId);
    }

    // Calculate quiz grade
    private float calculateQuizGrade(QuizSubmission submission) {
        // Fetch the Quiz object
        Quiz quiz = submission.getQuiz();

        // Fetch all questions for the quiz from QuestionBank
        List<QuestionBank> questionBanks = questionBankRepository.findByQuizzes(quiz);

        Map<Integer, String> studentAnswers = submission.getStudentAnswers();

        int correctAnswers = 0;
        int totalQuestions = 0;

        for (QuestionBank questionBank : questionBanks) {
            for (Question question : questionBank.getQuestions()) {
                totalQuestions++;

                if (studentAnswers.containsKey(question.getQuestionId())) {
                    String studentAnswer = studentAnswers.get(question.getQuestionId());
                    String correctAnswer = null;

                    if (question instanceof MCQQuestion) {
                        correctAnswer = ((MCQQuestion) question).getCorrectAnswer();
                    } else if (question instanceof TrueFalseQuestion) {
                        correctAnswer = ((TrueFalseQuestion) question).getCorrectAnswer();
                    } else if (question instanceof ShortAnswerQuestion) {
                        correctAnswer = ((ShortAnswerQuestion) question).getCorrectAnswer();
                    }

                    if (correctAnswer != null && correctAnswer.equalsIgnoreCase(studentAnswer)) {
                        correctAnswers++;
                    }
                }
            }
        }

        // Calculate percentage score
        return totalQuestions == 0 ? 0.0f : ((float) correctAnswers / totalQuestions) * 100.0f;
    }

}
