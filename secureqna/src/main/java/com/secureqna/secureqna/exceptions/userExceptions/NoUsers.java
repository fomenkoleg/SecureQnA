package com.secureqna.secureqna.exceptions.userExceptions;

public class NoUsers extends Exception{

    public NoUsers (){
        super("No hay usuarios actualmente en SecureQna");
    }
}
