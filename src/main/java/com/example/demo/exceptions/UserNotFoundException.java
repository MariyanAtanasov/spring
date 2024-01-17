package com.example.demo.exceptions;

public class UserNotFoundException extends Exception{
    private final static String message = "No such user!";

    public UserNotFoundException() { super(message);}
}
