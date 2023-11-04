package com.secureqna.secureqna.exceptions.userExceptions;

public class UsernameNotFoundException extends Exception{

    public UsernameNotFoundException(){
        super("El username es erroneo o no fue encontrado");
    }
}
