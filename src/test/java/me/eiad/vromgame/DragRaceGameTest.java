package me.eiad.vromgame;

import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;
import me.eiad.vromgame.rules.ValidationResults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DragRaceGameTest {

    private final DragRaceGame dragRaceGame = new DragRaceGame();

    private final List<Track> tracks = new ArrayList<>();

    @Test
    public void list_of_cars_should_not_be_null() {
        List<Car> cars = null;
        Round round = new Round(2);
        Race race = new Race(cars, round.getRoundNumber(), tracks);

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> dragRaceGame.checkIfValid(race));
    }

    @Test
    public void if_race_is_valid_should_return_true() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(200, 20, 2));
        cars.add(new Car(200, 20, 2));
        Round round = new Round(3);
        Race race = new Race(cars, round.getRoundNumber(), tracks);

        ValidationResults validationResults = dragRaceGame.checkIfValid(race);

        Assertions.assertFalse(validationResults.isValid());
    }

    @Test
    public void list_of_cars_should_not_be_empty() {
        List<Car> cars = new ArrayList<>();
        Round round = new Round(2);
        Race race = new Race(cars, round.getRoundNumber(), tracks);

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> dragRaceGame.checkIfValid(race));
    }

    @Test
    public void number_of_cars_should_be_more_than_two_cars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(200, 20, 2));
        Round round = new Round(3);
        Race race = new Race(cars, round.getRoundNumber(), tracks);

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> dragRaceGame.checkIfValid(race));
    }

    @Test
    public void numbers_of_rounds_should_not_be_less_than_two() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(200, 20, 2));
        cars.add(new Car(200, 20, 2));
        List<Round> rounds = new ArrayList<>();
        Round round = new Round(3);
        Race race = new Race(cars, round.getRoundNumber(), tracks);

        Assertions.assertThrows(RoundsShouldNotBeLessThanTwo.class,
                () -> dragRaceGame.checkIfValid(race));
    }

}
