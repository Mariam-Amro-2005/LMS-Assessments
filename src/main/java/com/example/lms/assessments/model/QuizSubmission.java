package com.example.lms.assessments.model;

import com.example.lms.assessments.model.QuizId;
import jakarta.persistence.*;
import java.util.List;

/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
DESKTOP-3EVP6M1\SQLEXPRESS*/

@Entity
@Table(name = "QuizSubmission")
public class QuizSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;

    @Embedded
    private QuizId quizKey;

    @Column(nullable = false)
    private Integer userId;

    @Lob
    //private byte[] submittedFile;

    private List<String> answers;

    // Getters and Setters
    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public QuizId getQuizKey() {
        return quizKey;
    }

    public void setQuizKey(QuizId quizKey) {
        this.quizKey = quizKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /*public byte[] getSubmittedFile() {
        return submittedFile;
    }

    public void setSubmittedFile(byte[] submittedFile) {
        this.submittedFile = submittedFile;
    }*/

    public List<String> getAnswers(){
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}

