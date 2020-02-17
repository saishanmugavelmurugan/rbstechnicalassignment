package com.rbs.RBSException;

public class InsufficientFundException extends RuntimeException {
    public InsufficientFundException() {
        super();
    }
    public InsufficientFundException(String message) {
        super(message);
    }
    public InsufficientFundException(String message, Throwable cause) {
        super(message, cause);
    }
}
