package me.eiad.vromgame.rules;

import java.util.ArrayList;
import java.util.List;

public class ValidationResults {

    private final List<ValidationResult> invalidResults = new ArrayList<>();

    public void add(ValidationResult result) {
        if (!result.isValid()) {
            invalidResults.add(result);
        }
    }

    public boolean isValid() {
        return invalidResults.isEmpty();
    }

    public List<ValidationResult> getInvalidResults() {
        return invalidResults;
    }
}
