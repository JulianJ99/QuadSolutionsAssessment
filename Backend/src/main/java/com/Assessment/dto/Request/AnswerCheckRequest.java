package com.Assessment.dto.Request;
import java.util.List;
import java.util.UUID;

import com.Assessment.Model.AnsweredQuestion;

public record AnswerCheckRequest(List<Answer> answers) {
    record Answer(UUID questionId, UUID answerId) {}

    public List<AnsweredQuestion> toDomain() {
        return answers.stream()
                .map(a -> new AnsweredQuestion(a.questionId(), a.answerId()))
                .toList();
    }

    public static AnswerCheckRequest fromDomain(List<AnsweredQuestion> answeredQuestions) {
        var answers = answeredQuestions.stream()
                .map(q -> new Answer(q.questionId(), q.answerId()))
                .toList();
        return new AnswerCheckRequest(answers);
    }
}