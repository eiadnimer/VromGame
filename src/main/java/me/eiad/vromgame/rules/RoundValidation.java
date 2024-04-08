package me.eiad.vromgame.rules;

import me.eiad.vromgame.core.Race;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;

public class RoundValidation implements Rule {
    @Override
    public void isValid(Race race) {
        if (race.getRounds().size() < 2) {
            throw new RoundsShouldNotBeLessThanTwo();
        }
    }
}
