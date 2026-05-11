package com.jspiders.exceptions;

public class BookingIdCannotBeFoundException extends RuntimeException {
    public BookingIdCannotBeFoundException(String message) {
        super(message);
    }
}
