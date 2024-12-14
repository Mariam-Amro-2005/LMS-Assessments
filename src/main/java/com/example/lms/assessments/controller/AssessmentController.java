package com.example.lms.assessments.controller;
/*import com.example.lms.assessments.dto.AssignmentRequest;
import com.example.lms.assessments.dto.QuizRequest;
import com.example.lms.assessments.model.Assignment;
import com.example.lms.assessments.model.Quiz;*/
import com.example.lms.assessments.model.Quiz;
import com.example.lms.assessments.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    @Autowired
    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping("/create-quiz")
    public String createQuiz(@RequestBody Quiz quiz) {
        return assessmentService.createQuiz(quiz);
    }

    /*@PostMapping("/create-assignment")
    public String createAssignment(@RequestBody Assignment assignment) {
        return assessmentService.createAssignment(assignment);
    }*/



        /*@PostMapping("/create-quiz")
        public String createQuiz(@RequestBody QuizRequest quizRequest) {
            return assessmentService.createQuiz(quizRequest);
        }



        @GetMapping("/quiz/{quizId}")
        public Quiz getQuizById(@PathVariable int quizId) {
            return assessmentService.getQuizById(quizId);
        }

        @GetMapping("/assignment/{assignmentId}")
        public Assignment getAssignmentById(@PathVariable int assignmentId) {
            return assessmentService.getAssignmentById(assignmentId);
        }*/
}


