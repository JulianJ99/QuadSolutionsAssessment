package com.Assessment.dto.Response;

import java.util.List;
import java.util.UUID;



public record QuestionListResponse(String sessionId, List<Question> questions) {
    record Question(UUID id, String question, List<Answer> answers) {
        static Question fromDomain(com.Assessment.Model.Question domainQuestion) {
            var answers = domainQuestion.allAnswersShuffled().stream()
                    .map(a -> new Answer(a.id(), a.answer()))
                    .toList();
            return new Question(domainQuestion.id(), domainQuestion.question(), answers);
        }
    }
    record Answer(UUID answerId, String answer){}

    public static QuestionListResponse fromDomain(String sessionId, List<com.Assessment.Model.Question> questions) {
        var questionResponses = questions.stream()
                .map(Question::fromDomain)
                .toList();

        return new QuestionListResponse(sessionId, questionResponses);
    }
}

