package me.eiad.vromgame;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;
import me.eiad.vromgame.exeptions.TracksShouldBeMoreThanOneTrack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RaceTest {
    private final Car carA = new Car(200, 20, 2);
    private final Car carB = new Car(200, 20, 2);
    private final Track trackA = new Track(100);
    private final Track trackB = new Track(200);

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
    public void round_one_should_start_before_round_two() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        race.start(List.of(carA, carB), 2, List.of(trackA, trackB));

        List<Integer> rounds = race.getRounds();

        Assertions.assertEquals(1, rounds.get(0));
        Assertions.assertEquals(2, rounds.get(1));
    }

    @Test
    public void after_first_round_finished_must_return_list_of_cars() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        List<Car> cars = race.start(List.of(carA, carB), 2, List.of(trackA, trackB));

        Assertions.assertEquals(List.of(carA, carB), cars);
    }
}
