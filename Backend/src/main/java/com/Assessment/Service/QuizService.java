package com.Assessment.Service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.Assessment.Model.AnswerResult;
import com.Assessment.Model.AnsweredQuestion;
import com.Assessment.Model.Question;
import com.Assessment.client.TriviaAPIClient;
import com.Assessment.Repository.iQuizRepository;
import com.Assessment.Repository.iTokenRepository;



@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);
    private final iQuizRepository quizRepository;
    private final iTokenRepository tokenRepository;
    private final TriviaAPIClient triviaClient;

    public QuizService(iQuizRepository quizRepository, iTokenRepository tokenRepository, TriviaAPIClient triviaClient) {
        this.quizRepository = quizRepository;
        this.tokenRepository = tokenRepository;
        this.triviaClient = triviaClient;
    }

    public List<Question> getQuestions(String sessionId, int amount, int category, String type, String difficulty) {
        var token = tokenRepository.findToken(sessionId)
                .orElseGet(() -> {
                    var newToken = triviaClient.requestNewToken();
                    tokenRepository.saveToken(sessionId, newToken);
                    return newToken;
                });

        try {
            return getQuestionsAndSaveCorrectAnswer(token, amount, category, type, difficulty);
        } catch (Exception ex) {
            logger.info("Token exhausted. Resetting token...");
            var resetToken = triviaClient.resetToken(token);
            return getQuestionsAndSaveCorrectAnswer(resetToken, amount, category, type, difficulty);
        }
    }

    public List<Question> getQuestionsAndSaveCorrectAnswer(String token, int amount, int category, String type, String difficulty ) {
        var questions = triviaClient.fetchQuestions(token, amount, category, type, difficulty);
        questions.forEach(question -> quizRepository.storeCorrectAnswer(question.id(), question.correct_answer().id()));
        return questions;
    }

    public List<AnswerResult> checkAnswers(List<AnsweredQuestion> answeredQuestions) {
        return answeredQuestions.stream()
                .map(q -> {
                    var correctId = quizRepository.getCorrectAnswer(q.questionId());
                    return correctId.map(uuid -> new AnswerResult(q.questionId(), q.answerId(), uuid)).orElse(null);
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
