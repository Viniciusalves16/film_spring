package com.example.demo.film.exception;

public class ErrorCustomizeYearException extends RuntimeException {
    private String message;

    public ErrorCustomizeYearException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }


}
