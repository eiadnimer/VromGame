package me.eiad.vromgame.rules;


import me.eiad.vromgame.Race;
import me.eiad.vromgame.exeptions.TracksShouldBeMoreThanOneTrack;

public class TrackValidation implements Rule {
    @Override
    public ValidationResult isValid(Race race) {
        if (race.getTracks().size() < 2) {
            throw new TracksShouldBeMoreThanOneTrack("tracks should be more than one track");
        }
        return new ValidationResult(true,"tracks should be more than one track");
    }
}
