package me.eiad.vromgame;

import me.eiad.vromgame.rules.*;

import java.util.ArrayList;
import java.util.List;

public class DragRaceGame {

    private final List<Rule> rules = new ArrayList<>();

    public DragRaceGame() {
        rules.add(new NumberOfCarsValidation());
        rules.add(new RoundValidation());
    }

    public ValidationResults checkIfValid(Race race) {
        ValidationResults validationResults = new ValidationResults();
        for (Rule rule : rules) {
            ValidationResult result = rule.isValid(race);
            validationResults.add(result);
        }
        return validationResults;
    }
}
