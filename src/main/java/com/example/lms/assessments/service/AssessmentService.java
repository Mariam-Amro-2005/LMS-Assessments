package com.example.lms.assessments.service;

import com.example.lms.assessments.dto.AssignmentRequest;
import com.example.lms.assessments.dto.QuizRequest;
import com.example.lms.assessments.model.Assessment;
import com.example.lms.assessments.model.Assignment;
import com.example.lms.assessments.model.AssignmentKey;
import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.model.QuizId;
import com.example.lms.assessments.repository.AssessmentRepository;
import com.example.lms.assessments.repository.AssignmentRepository;
import com.example.lms.assessments.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public AssessmentService(
            AssessmentRepository assessmentRepository,
            AssignmentRepository assignmentRepository,
            QuizRepository quizRepository) {
        this.assessmentRepository = assessmentRepository;
        this.assignmentRepository = assignmentRepository;
        this.quizRepository = quizRepository;
    }

    // Fetch Assignment by composite key (assignmentId, assessmentId)
    public Optional<Assignment> getAssignmentById(int assignmentId, int assessmentId) {
        return assignmentRepository.findById(new AssignmentKey(assignmentId, assessmentId));
    }

    // Fetch Quiz by composite key (quizId, assessmentId)
    public Optional<Quiz> getQuizById(int quizId, int assessmentId) {
        return quizRepository.findById(new QuizId(quizId, assessmentId));
    }

    // Create a new assignment
    public String createAssignment(AssignmentRequest assignmentRequest) {
        // Fetch associated Assessment
        Optional<Assessment> assessmentOpt = assessmentRepository.findById(assignmentRequest.getAssessmentId());
        //Optional<Assignment> assessmentOpt = assignmentRepository.findById(assignmentRequest.getAssessmentId());
        if (assessmentOpt.isEmpty()) {
            throw new IllegalArgumentException("Assessment not found for ID: " + assignmentRequest.getAssessmentId());
        }

        Assignment assignment = mapToAssignment(assignmentRequest, assessmentOpt.get());
        assignmentRepository.save(assignment);
        return "Assignment created successfully.";
    }

    // Create a new quiz
    public String createQuiz(QuizRequest quizRequest) {
        // Fetch associated Assessment
        Optional<Assessment> assessmentOpt = assessmentRepository.findById(quizRequest.getAssessmentId());
        //Optional<Quiz> assessmentOpt = quizRepository.findById(quizRequest.getAssessmentId());
        if (assessmentOpt.isEmpty()) {
            throw new IllegalArgumentException("Assessment not found for ID: " + quizRequest.getAssessmentId());
        }

        Quiz quiz = mapToQuiz(quizRequest, assessmentOpt.get());
        quizRepository.save(quiz);
        return "Quiz created successfully.";
    }

    private Assignment mapToAssignment(AssignmentRequest request, Assessment assessment) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentKey(new AssignmentKey(request.getAssignmentId(), assessment.getAssessmentId()));
        assignment.setQuestion(request.getQuestion());
        assignment.setAssessment(assessment); // Set reference to Assessment
        return assignment;
    }

    private Quiz mapToQuiz(QuizRequest request, Assessment assessment) {
        Quiz quiz = new Quiz();
        quiz.setQuizId(new QuizId(request.getQuizId(), assessment.getAssessmentId()));
        quiz.setTimeInMinutes(request.getTimeInMinutes());
        quiz.setAssessment(assessment); // Set reference to Assessment
        return quiz;
    }
}
