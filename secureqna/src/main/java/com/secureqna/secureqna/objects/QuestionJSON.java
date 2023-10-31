package com.secureqna.secureqna.objects;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class QuestionJSON {

    private String question;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctOption;

    public boolean checkAnswer(Answers possible){
        return possible.getContent().equals(correctOption);
    }

    //Constructor
    public QuestionJSON(Question question){
        this.question = question.getQuestion();
        this.optionA = question.getPossibleAnswer().get(0).getContent();
        this.optionB = question.getPossibleAnswer().get(1).getContent();
        this.optionC = question.getPossibleAnswer().get(2).getContent();
        this.optionD = question.getPossibleAnswer().get(3).getContent();
        if(this.optionA.equals(question.getCorrectAnswer())){
            this.correctOption="optionA";
        } else if(this.optionB.equals(question.getCorrectAnswer())){
            this.correctOption="optionB";
        } else if(this.optionC.equals(question.getCorrectAnswer())){
            this.correctOption="optionC";
        } else if(this.optionD.equals(question.getCorrectAnswer())){
            this.correctOption="optionD";
        }
    }
}
