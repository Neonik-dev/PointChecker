package se.ifmo.ru.web.lab4.pointchecker.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private final String error;
    private final String header;
    private final String description;

    public AuthException(String error, String header, String description) {
        super(error);
        this.error = error;
        this.header = header;
        this.description = description;
    }
}