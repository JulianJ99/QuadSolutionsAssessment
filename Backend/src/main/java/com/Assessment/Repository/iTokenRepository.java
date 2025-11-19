package com.Assessment.Repository;
import java.util.Optional;

public interface iTokenRepository {
    Optional<String> findToken(String sessionId);
    void saveToken(String sessionId, String token);
}
