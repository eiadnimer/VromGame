package me.eiad.vromgame.rules;
import me.eiad.vromgame.Race;
import me.eiad.vromgame.exeptions.TracksShouldBeMoreThanOneTrack;

public class TrackValidation implements Rule {
    @Override
    public void isValid(Race race) {
        if (race.getTracks().size() < 2) {
            throw new TracksShouldBeMoreThanOneTrack();
        }
        if (race.getNumberOfRounds() > race.getTracks().size()) {
            throw new TracksShouldBeMoreThanOneTrack();
        }
    }
}
