package com.christian.rocketseat.exceptions;

public class UserFoundException extends RuntimeException {
    
    public UserFoundException(){
        super("Usuário já existe!");
    }
}
