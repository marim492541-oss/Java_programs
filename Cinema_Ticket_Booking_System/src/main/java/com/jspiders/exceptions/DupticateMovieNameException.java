package com.jspiders.exceptions;

public class DupticateMovieNameException extends RuntimeException {
    public DupticateMovieNameException(String message) {
        super(message);
    }
}
