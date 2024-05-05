package me.eiad.vromgame.rules.race_rule;

import me.eiad.vromgame.core.Race;
import me.eiad.vromgame.exeptions.TracksShouldBeMoreThanOneTrack;

public class TrackValidation implements RaceRule {
    @Override
    public void isValid(Race race) {
        if (race.getTracks().size() < 2) {
            throw new TracksShouldBeMoreThanOneTrack();
        }
        if (race.getRounds().size() > race.getTracks().size()) {
            throw new TracksShouldBeMoreThanOneTrack();
        }
    }
}
