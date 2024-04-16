package me.eiad.vromgame;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.Race;
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
    private final Car carA = new Car(200, 10, 0.5);
    private final Car carB = new Car(220, 7, 1);
    private final Track trackA = new Track(3000);
    private final Track trackB = new Track(1000);

    @Test
    public void list_of_cars_should_not_be_null() {
        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> new Race(null, 2, List.of(trackA, trackB)));
    }

    @Test
    public void list_of_cars_should_not_be_empty() {
        List<Car> cars = new ArrayList<>();
        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> new Race(cars, 2, List.of(trackA, trackB)));
    }

    @Test
    public void minimum_two_cars_to_start_a_race() {
        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> new Race(List.of(carA), 2, List.of(trackA, trackB)));
    }

    @Test
    public void minimum_two_rounds_to_start_a_race() {
        Assertions.assertThrows(RoundsShouldNotBeLessThanTwo.class,
                () -> new Race(List.of(carA, carB), 1, List.of(trackA, trackB)));
    }

    @Test
    public void minimum_two_different_tracks_to_start_a_race() {
        Assertions.assertThrows(TracksShouldBeMoreThanOneTrack.class,
                () -> new Race(List.of(carA, carB), 2, List.of(trackA)));
    }

    @Test
    public void if_number_of_rounds_is_higher_than_number_of_tracks_must_fail() {
        Assertions.assertThrows(TracksShouldBeMoreThanOneTrack.class,
                () -> new Race(List.of(carA, carB), 3, List.of(trackA, trackB)));
    }

    @Test
    public void make_sure_that_the_winner_of_the_round_is_the_car_with_less_time_to_finish_the_track() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        Map<Car, Double> result = race.start();

        Double carATime = result.get(carA);
        Double carBTime = result.get(carB);
        Car winner = race.getWinner(1);

        Assertions.assertTrue(carATime < carBTime);
        Assertions.assertEquals(carA,winner);
    }

    @Test
    public void make_sure_that_the_loser_car_is_the_car_with_the_highest_time_to_finish_the_track() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        Map<Car, Double> result = race.start();

        Double carATime = result.get(carA);
        Double carBTime = result.get(carB);
        Car loser = race.getLoser(1);

        Assertions.assertTrue(carBTime > carATime);
        Assertions.assertEquals(loser,carB);
    }


}
