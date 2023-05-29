package com.example.demo.model;

public class ValidateResult {
    private final boolean isValid;
    private final String errorMessage;

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ValidateResult success() {
        return new ValidateResult(true, "");
    }

    public static ValidateResult failed(String errorMessage) {
        return new ValidateResult(false, errorMessage);
    }

    private ValidateResult(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }
}
