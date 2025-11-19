package com.Assessment.Controller;


import com.Assessment.Service.QuizService;
import com.Assessment.dto.Request.AnswerCheckRequest;
import com.Assessment.dto.Response.AnswerCheckResponse;
import com.Assessment.dto.Response.QuestionListResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("questions")
    public ResponseEntity<QuestionListResponse> listQuestions(
            @RequestHeader("X-Session-Id") String sessionId,
            @RequestHeader("Difficulty") String difficulty
    ) {
        var questions = quizService.getQuestions(sessionId, 10, 15, "multiple", difficulty);
        var response = QuestionListResponse.fromDomain(sessionId, questions);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("answers")
    public ResponseEntity<AnswerCheckResponse> checkAnswers(
            @RequestBody AnswerCheckRequest request
        ) {
            var answeredQuestions = request.toDomain();
            var results = quizService.checkAnswers(answeredQuestions);
            var response = AnswerCheckResponse.fromDomain(results);
            return ResponseEntity.ok(response);
    }
}


