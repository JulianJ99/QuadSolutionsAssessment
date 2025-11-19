package com.Assessment.dto.Response;

import java.util.List;
import java.util.UUID;

import com.Assessment.Model.AnswerResult;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerCheckResponse(List<Result>Results) {
    record Result(UUID questionId, UUID answerId, UUID correctAnswerId) {
        @JsonProperty("Correct")
        public boolean Correct(){
            return answerId.equals(correctAnswerId);
        }
    
        static Result fromDomain(AnswerResult answerResult) {
            return new Result(
                    answerResult.questionId(),
                    answerResult.answerId(),
                    answerResult.correctAnswerId()
            );
        }
    }

    public static AnswerCheckResponse fromDomain(List<AnswerResult> results) {
        var dtoResults = results.stream()
                .map(Result::fromDomain)
                .toList();   
        return new AnswerCheckResponse(dtoResults);
    }

}
