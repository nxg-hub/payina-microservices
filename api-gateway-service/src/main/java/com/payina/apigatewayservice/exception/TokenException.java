package com.payina.apigatewayservice.exception;

public class TokenException extends RuntimeException{
    public TokenException(String message) {
        super(message);
    }
}
