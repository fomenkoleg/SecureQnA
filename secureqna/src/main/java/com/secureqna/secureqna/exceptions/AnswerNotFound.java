package com.secureqna.secureqna.exceptions;

public class AnswerNotFound extends Exception{

    public AnswerNotFound(){
        super("Answer does not exist");
    }
}
