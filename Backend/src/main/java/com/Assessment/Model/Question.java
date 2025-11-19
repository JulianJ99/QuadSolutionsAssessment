package com.Assessment.Model;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public record Question(
 
    UUID id,

    String question,

    String category,

    String difficulty,

    String type,
    
    Answer correct_answer,

    List<Answer> incorrect_answers
){

    public List<Answer> allAnswersShuffled() {
        List<Answer> answers = new ArrayList<>(incorrect_answers);
        answers.add(correct_answer);
        Collections.shuffle(answers);
        return answers;
    }
}

