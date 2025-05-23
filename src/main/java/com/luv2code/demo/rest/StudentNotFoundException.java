package com.luv2code.demo.rest;

public class StudentNotFoundException extends RuntimeException {

    //generate Constructor from super class
    //may not use all of them, good to have them in case need in future
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
