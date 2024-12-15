package com.example.lms.assessments.service;

import com.example.lms.assessments.model.AssignmentKey;
import com.example.lms.assessments.model.AssignmentSubmission;
import com.example.lms.assessments.model.Grades;
import com.example.lms.assessments.model.QuizId;
import com.example.lms.assessments.model.QuizSubmission;
import com.example.lms.assessments.repository.AssignmentSubmissionRepository;
import com.example.lms.assessments.repository.GradesRepository;
import com.example.lms.assessments.repository.QuizSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradingService {

    private final GradesRepository gradesRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Autowired
    public GradingService(
            GradesRepository gradesRepository,
            QuizSubmissionRepository quizSubmissionRepository,
            AssignmentSubmissionRepository assignmentSubmissionRepository) {
        this.gradesRepository = gradesRepository;
        this.quizSubmissionRepository = quizSubmissionRepository;
        this.assignmentSubmissionRepository = assignmentSubmissionRepository;
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

    private float calculateQuizGrade(QuizSubmission submission) {
        // Logic to compare submission answers with correct answers
        return 90.0f; // Placeholder for now
    }
}
