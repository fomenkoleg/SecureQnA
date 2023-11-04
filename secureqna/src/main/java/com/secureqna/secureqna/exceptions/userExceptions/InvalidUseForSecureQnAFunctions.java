package com.secureqna.secureqna.exceptions.userExceptions;

public class InvalidUseForSecureQnAFunctions extends Exception{

    public InvalidUseForSecureQnAFunctions(){
        super("Se han intentado usurpar las funciones de SecureQnA");
    }
}
