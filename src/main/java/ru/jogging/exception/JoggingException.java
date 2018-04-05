package ru.jogging.exception;

public class JoggingException extends Exception {
    public JoggingException(String message) {
        super(message);
    }

    public JoggingException(String message, Throwable cause) {
        super(message, cause);
    }
}
