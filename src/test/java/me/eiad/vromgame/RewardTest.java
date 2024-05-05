package me.eiad.vromgame;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.RewardSystemImpl;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RewardTest {

    private final Car carA = new Car(200, 10, 1);
    private final Car carB = new Car(220, 7, 2);

    @Test
    public void rounds_should_not_be_zero() {
        Assertions.assertThrows(RoundsShouldNotBeLessThanTwo.class,
                () -> new RewardSystemImpl(0));
    }

    @Test
    public void cars_should_not_be_empty() {
        List<Car> cars = new ArrayList<>();
        RewardSystemImpl rewardSystemImpl = new RewardSystemImpl(2);

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class
                , () -> rewardSystemImpl.segregatePoints(cars, 1));
    }

    @Test
    public void first_place_of_round_should_takes_65_form_the_points_for_this_round() {
        RewardSystemImpl rewardSystemImpl = new RewardSystemImpl(2);

        rewardSystemImpl.segregatePoints(List.of(carA, carB), 1);

        Assertions.assertEquals(65, carA.getUpgradePoints());
    }

    @Test
    public void second_place_should_takes_65_from_the_remaining_points() {
        RewardSystemImpl rewardSystemImpl = new RewardSystemImpl(2);

        rewardSystemImpl.segregatePoints(List.of(carA, carB), 1);

        Assertions.assertEquals(22.75, carB.getUpgradePoints());
    }
}
