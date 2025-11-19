package com.Assessment.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class TokenRepository implements iTokenRepository {

    private final ConcurrentHashMap<String, String> tokenStorage = new ConcurrentHashMap<>();

    @Override
    public Optional<String> findToken(String sessionId) {
        return Optional.ofNullable(tokenStorage.get(sessionId));
    }

    @Override
    public void saveToken(String sessionId, String token) {
        tokenStorage.put(sessionId, token);
    }
}
