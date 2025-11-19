package com.Assessment.Model;

import java.util.UUID;

public record AnsweredQuestion (

    UUID questionId,

    UUID answerId
) {}
