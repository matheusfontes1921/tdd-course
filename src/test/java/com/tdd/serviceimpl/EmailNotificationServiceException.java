package com.tdd.serviceimpl;

public class EmailNotificationServiceException extends RuntimeException {
    public EmailNotificationServiceException (String message) {
        super(message);
    }
}
