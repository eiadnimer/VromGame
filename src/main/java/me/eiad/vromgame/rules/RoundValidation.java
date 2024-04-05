package me.eiad.vromgame.rules;


import me.eiad.vromgame.Race;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;

public class RoundValidation implements Rule {
    @Override
    public ValidationResult isValid(Race race) {
        if (race.getRound() < 2) {
            throw new RoundsShouldNotBeLessThanTwo();
        }
        return new ValidationResult(true, "numbers of rounds should be more than one round");
    }
}
