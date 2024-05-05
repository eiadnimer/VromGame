package me.eiad.vromgame.rules.race_rule;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.Race;
import me.eiad.vromgame.exeptions.CarsShouldNotBeTheSame;

import java.util.HashSet;
import java.util.Set;

public class CarsEqual implements RaceRule {
    @Override
    public void isValid(Race race) {
        Set<Car> cars = new HashSet<>(race.getCars());
        if (cars.size()!=race.getCars().size()){
            throw new CarsShouldNotBeTheSame();
        }
    }
}
