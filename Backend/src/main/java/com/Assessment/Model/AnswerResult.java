package com.Assessment.Model;

import java.util.UUID;

public record AnswerResult(
        UUID questionId,
        
        UUID answerId,
        
        UUID correctAnswerId
) {}