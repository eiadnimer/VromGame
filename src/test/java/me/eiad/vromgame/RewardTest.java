package me.eiad.vromgame;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.Reward;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RewardTest {

    @Test
    public void rounds_should_not_be_zero() {
        Assertions.assertThrows(RoundsShouldNotBeLessThanTwo.class,
                () -> new Reward(0));
    }

    @Test
    public void cars_should_not_be_empty() {
        List<Car> cars = new ArrayList<>();
        Reward reward = new Reward(2);

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class
                , () -> reward.segregatePoints(cars, 1));
    }
}
