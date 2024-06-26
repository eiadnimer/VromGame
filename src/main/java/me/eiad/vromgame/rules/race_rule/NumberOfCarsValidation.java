package me.eiad.vromgame.rules.race_rule;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.Race;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;

import java.util.List;

public class NumberOfCarsValidation implements RaceRule {
    @Override
    public void isValid(Race race) {
        List<Car> cars = race.getCars();
        validate(cars);
    }

    private void validate(List<Car> cars) {
        if (cars == null) {
            throw new CarsShouldBeMoreThanOneCar();
        }
        if (cars.isEmpty()) {
            throw new CarsShouldBeMoreThanOneCar();
        }
        if (cars.size() < 2) {
            throw new CarsShouldBeMoreThanOneCar();
        }
    }
}
