package com.nxg.payina.exceptions;

public class IncorrectTransactionPinException extends RuntimeException {
    public IncorrectTransactionPinException(String message) {
        super(message);
    }
}
