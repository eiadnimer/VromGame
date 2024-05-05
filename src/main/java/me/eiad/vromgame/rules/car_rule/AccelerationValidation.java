package me.eiad.vromgame.rules.car_rule;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.exeptions.AccelerationShouldBePositive;

public class AccelerationValidation implements CarRule{
    @Override
    public void isValid(Car car) {
        if (car.getAcceleration() < 0) {
            throw new AccelerationShouldBePositive();
        }
    }
}
