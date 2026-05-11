package com.jspiders.ems.exceptions;

public class DuplicateEmployeeIdException extends RuntimeException
{
    public DuplicateEmployeeIdException(String message)
    {
        super(message);
    }
}
