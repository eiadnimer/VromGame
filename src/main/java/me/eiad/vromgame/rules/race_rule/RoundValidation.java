package me.eiad.vromgame.rules.race_rule;

import me.eiad.vromgame.core.Race;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;

public class RoundValidation implements RaceRule {
    @Override
    public void isValid(Race race) {
        if (race.getRounds().size() < 2) {
            throw new RoundsShouldNotBeLessThanTwo();
        }
    }
}
