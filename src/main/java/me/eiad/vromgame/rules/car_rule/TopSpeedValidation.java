package me.eiad.vromgame.rules.car_rule;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.exeptions.TopSpeedShouldBePositive;

public class TopSpeedValidation implements CarRule {
    @Override
    public void isValid(Car car) {
        if (car.getTopSpeed() < 0) {
            throw new TopSpeedShouldBePositive();
        }
    }
}
