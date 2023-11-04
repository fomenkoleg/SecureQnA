package com.secureqna.secureqna.exceptions.userExceptions;

public class UserNotFound extends Exception{

    public UserNotFound(){
        super("El usuario no ha sido encontrado");
    }
}
