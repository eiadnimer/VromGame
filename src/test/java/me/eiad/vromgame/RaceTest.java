package me.eiad.vromgame;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.Race;
import me.eiad.vromgame.core.Round;
import me.eiad.vromgame.core.Track;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;
import me.eiad.vromgame.exeptions.TracksShouldBeMoreThanOneTrack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RaceTest {
    private final Car carA = new Car(200, 10, 3);
    private final Car carB = new Car(220, 2, 2);
    private final Round round1 = new Round(15);
    private final Round round2 = new Round(35);
    private final Round round3 = new Round(25);
    private final Track trackA = new Track(100);
    private final Track trackB = new Track(200);

    @Test
    public void list_of_cars_should_not_be_null() {
        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> new Race(null, List.of(round1, round2), List.of(trackA, trackB)));
    }

    @Test
    public void list_of_cars_should_not_be_empty() {
        List<Car> cars = new ArrayList<>();
        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> new Race(cars, List.of(round1, round2), List.of(trackA, trackB)));
    }

    @Test
    public void minimum_two_cars_to_start_a_race() {
        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> new Race(List.of(carA), List.of(round1, round2), List.of(trackA, trackB)));
    }

    @Test
    public void minimum_two_rounds_to_start_a_race() {
        Assertions.assertThrows(RoundsShouldNotBeLessThanTwo.class,
                () -> new Race(List.of(carA, carB), List.of(round1), List.of(trackA, trackB)));
    }

    @Test
    public void minimum_two_different_tracks_to_start_a_race() {
        Assertions.assertThrows(TracksShouldBeMoreThanOneTrack.class,
                () -> new Race(List.of(carA, carB), List.of(round1,round2), List.of(trackA)));
    }

    @Test
    public void if_number_of_rounds_is_higher_than_number_of_tracks_must_fail() {
        Assertions.assertThrows(TracksShouldBeMoreThanOneTrack.class,
                () -> new Race(List.of(carA, carB), List.of(round1, round2, round3), List.of(trackA, trackB)));
    }

    @Test
    public void each_round_must_have_different_track() {
        Race race = new Race((List.of(carA, carB)), List.of(round1,round2), List.of(trackA, trackB));
        race.start();

        Map<Round, Track> map = race.getRandomPicks();

        Assertions.assertNotEquals(map.get(round1), map.get(round2));
    }

    @Test
    public void make_sure_that_the_fastest_car_is_the_car_with_less_time_to_finish_the_track() {
        Race race = new Race((List.of(carA, carB)), List.of(round1,round2), List.of(trackA, trackB));
        List<Car> cars = race.start();

        Assertions.assertEquals(cars.get(0), race.getWinner());
    }
}
