// src/main/java/com/example/github/exception/RateLimitExceededException.java
package com.example.github.exception;

public class RateLimitExceededException extends RuntimeException {
    public RateLimitExceededException(String message) {
        super(message);
    }
}
