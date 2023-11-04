package com.secureqna.secureqna.exceptions.userExceptions;

public class UserOnRegisterAlreadyIn extends Exception{

    public UserOnRegisterAlreadyIn(){
        super("Este usuario ya existe");
    }
}
