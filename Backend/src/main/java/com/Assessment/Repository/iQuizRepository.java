package com.Assessment.Repository;

import java.util.Optional;
import java.util.UUID;

public interface iQuizRepository {

    void storeCorrectAnswer(UUID questionId, UUID correctAnswerId);

    Optional<UUID> getCorrectAnswer(UUID questionId);
}
