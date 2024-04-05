package me.eiad.vromgame.rules;

public class ValidationResult {
    private final boolean valid;
    private final String errorMessage;


    public ValidationResult(boolean valid) {
        this(valid, "");
    }


    public ValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isValid() {
        return valid;
    }
}
