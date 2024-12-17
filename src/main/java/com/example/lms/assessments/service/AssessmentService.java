package com.example.lms.assessments.service;

import com.example.lms.assessments.dto.AssignmentRequest;
import com.example.lms.assessments.dto.QuizRequest;
import com.example.lms.assessments.model.Assessment;
import com.example.lms.assessments.model.Assignment;
import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.repository.AssessmentRepository;
import com.example.lms.assessments.repository.AssignmentRepository;
import com.example.lms.assessments.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // Fetch Assignment by ID
    public Optional<Assignment> getAssignmentById(int assessmentId) {
        return assignmentRepository.findById(assessmentId);
    }

    // Fetch Quiz by ID
    public Optional<Quiz> getQuizById(int assessmentId) {
        return quizRepository.findById(assessmentId);
    }

    // Create a new Assignment
    public String createAssignment(AssignmentRequest request) {
        Assignment assignment = mapToAssignment(request);
        assignmentRepository.save(assignment);
        return "Assignment created successfully.";
    }

    // Create a new Quiz
    public String createQuiz(QuizRequest request) {
        Quiz quiz = mapToQuiz(request);
        quizRepository.save(quiz);
        return "Quiz created successfully.";
    }

    // Map AssignmentRequest to Assignment entity
    private Assignment mapToAssignment(AssignmentRequest request) {
        Assignment assignment = new Assignment();
        assignment.setQuestion(request.getQuestion());        // Specific to Assignment
        assignment.setDueDate(request.getDueDate());          // Inherited from Assessment
        assignment.setMaxScore(request.getMaxScore());        // Inherited from Assessment
        assignment.setCourseId(request.getCourseId());        // Inherited from Assessment
        assignment.setCreated_date(LocalDate.now());          // Set creation date
        assignment.setType("assignment");                     // Type identifier
        return assignment;
    }

    // Map QuizRequest to Quiz entity
    private Quiz mapToQuiz(QuizRequest request) {
        Quiz quiz = new Quiz();
        quiz.setTimeInMinutes(request.getTimeInMinutes());    // Specific to Quiz
        quiz.setDueDate(request.getDueDate());                // Inherited from Assessment
        quiz.setMaxScore(request.getMaxScore());              // Inherited from Assessment
        quiz.setCourseId(request.getCourseId());              // Inherited from Assessment
        quiz.setCreated_date(LocalDate.now());                // Set creation date
        quiz.setType("quiz");                                 // Type identifier
        return quiz;
    }
}
