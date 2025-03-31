// src/main/java/com/example/github/exception/UserNotFoundException.java
package com.example.github.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
