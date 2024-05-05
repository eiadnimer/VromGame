package me.eiad.vromgame.rules.car_rule;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.exeptions.TimeIsMinus;

public class WarmupTimeValidation implements CarRule {
    @Override
    public void isValid(Car car) {
        if (car.getWarmUpTime() < 0) {
            throw new TimeIsMinus();
        }
    }
}
