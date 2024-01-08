package com.nxg.payina.exceptions;

public class AccountExpiredException extends RuntimeException{
    public AccountExpiredException(String message) {
        super(message);
    }
    
}
