package com.payina.apigatewayservice.exception;

public class InvalidTokenException extends RuntimeException {


    public InvalidTokenException(String message) {
        super(message);
    }
}
