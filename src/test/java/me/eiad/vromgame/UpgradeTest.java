package me.eiad.vromgame;

import me.eiad.vromgame.core.Augmentation;
import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.UpgradeSystemImpl;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UpgradeTest {

    @Test
    public void list_of_cars_should_not_be_null() {
        UpgradeSystemImpl upgrade = new UpgradeSystemImpl();

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> upgrade.upgrade(null, List.of(Augmentation.WARMUP_TIME, Augmentation.ACCELERATIONS,
                        Augmentation.TOP_SPEED)));
    }

    @Test
    public void list_of_cars_should_not_be_empty() {
        List<Car> cars = new ArrayList<>();
        UpgradeSystemImpl upgrade = new UpgradeSystemImpl();

        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> upgrade.upgrade(cars, List.of(Augmentation.WARMUP_TIME, Augmentation.ACCELERATIONS,
                        Augmentation.TOP_SPEED)));
    }

}
