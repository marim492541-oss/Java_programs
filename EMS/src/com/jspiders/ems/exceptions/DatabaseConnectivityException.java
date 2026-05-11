package com.jspiders.ems.exceptions;

public class DatabaseConnectivityException extends RuntimeException
{
    public DatabaseConnectivityException(String message)
    {
        super(message);
    }
}
