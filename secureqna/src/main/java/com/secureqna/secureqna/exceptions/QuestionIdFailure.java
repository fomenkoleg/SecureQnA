package com.secureqna.secureqna.exceptions;

public class QuestionIdFailure extends Exception{

    public QuestionIdFailure(){
        super("El id se esta repitiendo");
    }
}
