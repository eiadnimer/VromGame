package me.eiad.vromgame;

import me.eiad.vromgame.core.*;
import me.eiad.vromgame.exeptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RaceTest {

    private final MockUpgrade upgrade = new MockUpgrade();
    private final MockReward reward = new MockReward();
    private final Car carA = new Car(200, 10, 2);
    private final Car carB = new Car(220, 7, 2);
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
    public void you_cant_start_a_race_with_same_cars() {
        Assertions.assertThrows(CarsShouldNotBeTheSame.class,
                () -> new Race((List.of(carA, carA)), 2, List.of(trackA, trackB)));
    }

    @Test
    public void after_finish_the_race_must_return_list_of_winners() {
        Race race = new Race(List.of(carA, carB), 2, List.of(trackA, trackB));
        race.start();

        List<Car> winners = race.getWinners();

        Assertions.assertEquals(1, winners.size());
    }

    @Test
    public void winners_of_round_should_be_all_the_cars_except_the_last_car() {
        Car carC = new Car(330, 5, 3);
        Car carD = new Car(220, 3, 1);
        Car carE = new Car(260, 8, 2);
        Car carF = new Car(300, 4, 0.5);
        Track trackC = new Track(500);
        Track trackD = new Track(2000);
        Track trackE = new Track(2500);
        Track trackF = new Track(2200);
        Race race = new Race((List.of(carA, carB, carC, carD, carE, carF)), 4,
                List.of(trackA, trackB, trackC, trackD, trackE, trackF));
        race.start();

        List<Car> winners = race.getWinners(2);

        Assertions.assertEquals(5, winners.size());
    }

    @Test
    public void make_sure_that_the_winner_of_the_round_is_the_car_with_less_time_to_finish_the_track() {
        Car carC = new Car(330, 5, 3);
        Car carD = new Car(220, 3, 2);
        Car carE = new Car(260, 8, 2);
        Car carF = new Car(300, 4, 2);
        Track trackC = new Track(500);
        Track trackD = new Track(2000);
        Track trackE = new Track(2500);
        Track trackF = new Track(2200);
        Race race = new Race((List.of(carA, carB, carC, carD, carE, carF)), 2,
                List.of(trackA, trackB, trackC, trackD, trackE, trackF));
        race.start();

        Car winner = race.getWinner(2);

        Assertions.assertEquals(carA, winner);
    }


    @Test
    public void make_sure_that_the_loser_of_the_round_is_the_car_with_the_highest_time_to_finish_the_track() {
        Car carC = new Car(200, 5, 10);
        Race race = new Race((List.of(carA, carB,carC)), 2, List.of(trackA, trackB));
        race.start();

        Car loser = race.getLoser(2);

        Assertions.assertEquals(loser, carC);
    }

    @Test
    public void cant_check_the_result_of_round_if_the_round_do_not_starts_yet() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        race.start();

        Assertions.assertThrows(RoundsShouldStartsSequentially.class,
                () -> race.getWinner(3));
    }

    @Test
    public void each_car_should_return_report() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        race.start();

        Assertions.assertNotNull(race.getReport(1));
    }

    @Test
    public void the_winner_for_the_race_is_the_winner_of_the_last_round_of_the_race() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        race.start();

        Assertions.assertEquals(carA, race.getWinner());
    }

    @Test
    public void make_sure_to_segregate_the_points_after_each_round() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        race.start();

        reward.segregatePoints(List.of(carA, carB), 1);

        Assertions.assertTrue(reward.isCalled());
    }

    @Test
    public void make_sure_that_the_last_car_should_takes_zero_points() {
        Race race = new Race((List.of(carA, carB)), 2, List.of(trackA, trackB));
        race.start();

        reward.segregatePoints(List.of(carA, carB), 1);

        Assertions.assertEquals(0, carB.getUpgradePoints());
    }


    @Test
    public void after_each_round_the_winner_cars_should_choose_between_three_random_augmentation_to_upgrade_there_cars() {
        Car carC = new Car(500, 100, 2);
        Race race = new Race((List.of(carA, carB, carC)), 2, List.of(trackA, trackB));
        List<Car> winners = race.start();

        List<Augmentation> augmentations = race.getAugmentations();
        upgrade.upgrade(winners, augmentations);

        Assertions.assertTrue(upgrade.isCalled());
    }
}