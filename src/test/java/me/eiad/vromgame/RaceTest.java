package me.eiad.vromgame;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;
import me.eiad.vromgame.exeptions.TracksShouldBeMoreThanOneTrack;
import me.eiad.vromgame.rules.ValidationResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RaceTest {

    private final Car carA = new Car(200, 20, 2);
    private final Car carB = new Car(200, 20, 2);
    private final Track trackA = new Track(100);
    private final Track trackB = new Track(200);

    @Test
    public void list_of_cars_should_not_be_null() {
        Round round = new Round(2);
        Race race = new Race(null, round.getRoundNumber(), List.of(trackA, trackB));

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class, race::checkIfValid);
    }

    @Test
    public void minimum_two_cars_to_start_a_race() {
        Round round = new Round(3);
        Race race = new Race(List.of(carA), round.getRoundNumber(), List.of(trackA, trackB));

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class, race::checkIfValid);
    }

    @Test
    public void minimum_two_rounds_to_start_a_race() {
        Round round = new Round(1);
        Race race = new Race(List.of(carA, carB), round.getRoundNumber(), List.of(trackA, trackB));

        Assertions.assertThrows(RoundsShouldNotBeLessThanTwo.class, race::checkIfValid);
    }

    @Test
    public void minimum_two_different_tracks_to_start_a_race() {
        Round round = new Round(3);

        Race race = new Race(List.of(carA, carB), round.getRoundNumber(), List.of(trackA));

        Assertions.assertThrows(TracksShouldBeMoreThanOneTrack.class,
                race::checkIfValid);
    }

    @Test
    public void if_race_is_valid_race_ready_to_start() {
        Round round = new Round(3);

        Race race = new Race(List.of(carA, carB), round.getRoundNumber(), List.of(trackA, trackB));
        ValidationResults validationResults = race.checkIfValid();

        Assertions.assertTrue(validationResults.isValid());
    }

    @Test
    public void round_one_should_start_before_round_two() {
        Round round = new Round(3);

        Race race = new Race(List.of(carA,carB), round.getRoundNumber(), List.of(trackA,trackB));
    }
}
