package com.nxg.payina.exceptions;

public class EmailAlreadyTakenException extends RuntimeException{

    public EmailAlreadyTakenException(String message){
        super(message);
    }
}
