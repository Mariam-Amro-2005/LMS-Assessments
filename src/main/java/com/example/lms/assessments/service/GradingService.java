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
    private final QuestionBankRepository questionBankRepository;

    @Autowired
    public GradingService(
            GradesRepository gradesRepository,
            QuizSubmissionRepository quizSubmissionRepository,
            AssignmentSubmissionRepository assignmentSubmissionRepository,
            QuestionBankRepository questionBankRepository) {
        this.gradesRepository = gradesRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
        this.questionBankRepository = questionBankRepository;
    }

    // Automatic grading for quizzes
    public String autoGradeQuiz(Integer userId, Integer assessmentId, Integer quizId) {
        QuizId quizKey = new QuizId(assessmentId, quizId);
        Optional<QuizSubmission> submissionOpt = quizSubmissionRepository.findByQuizKeyAndUserId(quizKey, userId);

        if (submissionOpt.isEmpty()) {
            throw new IllegalArgumentException("Submission not found for quiz.");
        }

        QuizSubmission submission = submissionOpt.get();
        float calculatedGrade = calculateQuizGrade(submission);

        Grades grade = new Grades();
        grade.setUserId(userId);
        grade.setQuizKey(quizKey);
        grade.setGrade(calculatedGrade);

        gradesRepository.save(grade);
        return "Quiz graded successfully. Grade: " + calculatedGrade;
    }

    // Manual grading for assignments
    public String manualGradeAssignment(Integer userId, Integer assessmentId, Integer assignmentId, Float grade, String feedback) {
        AssignmentKey assignmentKey = new AssignmentKey(assessmentId, assignmentId);
        Optional<AssignmentSubmission> submissionOpt = assignmentSubmissionRepository.findByAssignmentKeyAndUserId(assignmentKey, userId);

        if (submissionOpt.isEmpty()) {
            throw new IllegalArgumentException("Submission not found for assignment.");
        }

        Grades grades = new Grades();
        grades.setUserId(userId);
        grades.setAssignmentKey(assignmentKey);
        grades.setGrade(grade);
        grades.setFeedback(feedback);

        gradesRepository.save(grades);
        return "Assignment graded successfully. Grade: " + grade;
    }

    // Fetch grades for a specific user and assessment
    public Optional<Grades> getGradeByUserAndQuiz(Integer userId, QuizId quizKey) {
        return gradesRepository.findByUserIdAndQuizKey(userId, quizKey);
    }

    public Optional<Grades> getGradeByUserAndAssignment(Integer userId, AssignmentKey assignmentKey) {
        return gradesRepository.findByUserIdAndAssignmentKey(userId, assignmentKey);
    }

    // Calculate quiz grade
    private float calculateQuizGrade(QuizSubmission submission) {
        // Fetch all questions for the quiz from QuestionBank
        List<QuestionBank> questionBanks = questionBankRepository.findByIdQuizKey(submission.getQuizKey());

        // Student answers
        Map<Integer, String> studentAnswers = submission.getStudentAnswers();

        // Grading logic
        int correctAnswers = 0;
        int totalQuestions = questionBanks.size();

        for (QuestionBank questionBank : questionBanks) {
            Question question = questionBank.getQuestion();

            if (studentAnswers.containsKey(question.getQuestionId())) {
                String studentAnswer = studentAnswers.get(question.getQuestionId());
                String correctAnswer = null;

                // Fetch correct answer based on question type
                if (question instanceof MCQQuestion) {
                    correctAnswer = ((MCQQuestion) question).getCorrectAnswer();
                } else if (question instanceof TrueFalseQuestion) {
                    correctAnswer = ((TrueFalseQuestion) question).getCorrectAnswer();
                } else if (question instanceof ShortAnswerQuestion) {
                    correctAnswer = ((ShortAnswerQuestion) question).getCorrectAnswer();
                }

                // Compare answers
                if (correctAnswer != null && correctAnswer.equalsIgnoreCase(studentAnswer)) {
                    correctAnswers++;
                }
            }
        }

        // Calculate percentage score
        if (totalQuestions == 0) {
            return 0.0f; // Avoid division by zero
        }
        return ((float) correctAnswers / totalQuestions) * 100.0f;
    }
}
