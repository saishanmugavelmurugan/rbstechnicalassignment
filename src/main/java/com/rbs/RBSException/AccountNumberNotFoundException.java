package com.rbs.RBSException;

public class AccountNumberNotFoundException extends RuntimeException {
    public AccountNumberNotFoundException() {
        super();
    }
    public AccountNumberNotFoundException(String message) {
        super(message);
    }
    public AccountNumberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
