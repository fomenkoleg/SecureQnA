package com.secureqna.secureqna.exceptions;

public class QuestionNotFound extends Exception{

    public QuestionNotFound(){
        super("La pregunta solicitada no existe o no ha sido encontrada");
    }

}
