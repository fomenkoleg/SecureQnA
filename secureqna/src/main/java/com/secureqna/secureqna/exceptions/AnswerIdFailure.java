package com.secureqna.secureqna.exceptions;

public class AnswerIdFailure extends Exception{

    public AnswerIdFailure(){
        super("Answer exists or its id is chosen");
    }
}
