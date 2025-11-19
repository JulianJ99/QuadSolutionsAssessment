package com.Assessment.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class QuizRepository implements iQuizRepository {
    private final Map<UUID, UUID> correctAnswersByQuestionId = new HashMap<>();

    @Override
    public void storeCorrectAnswer(UUID questionId, UUID correctAnswerId) {
        correctAnswersByQuestionId.put(questionId, correctAnswerId);
    }

    @Override
    public Optional<UUID> getCorrectAnswer(UUID questionId) {
        return Optional.ofNullable(correctAnswersByQuestionId.get(questionId));
    }
}
