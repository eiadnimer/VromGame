package me.eiad.vromgame;

import me.eiad.vromgame.core.Augmentation;
import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.exeptions.AccelerationShouldBePositive;
import me.eiad.vromgame.exeptions.TimeIsMinus;
import me.eiad.vromgame.exeptions.TopSpeedShouldBePositive;
import me.eiad.vromgame.exeptions.WarmupTimeIsMinimumValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    public void top_speed_should_be_positive_number() {
        Assertions.assertThrows(TopSpeedShouldBePositive.class,
                () -> new Car(-100, 50, 2));
    }

    @Test
    public void accelerations_should_be_positive_number() {
        Assertions.assertThrows(AccelerationShouldBePositive.class,
                () -> new Car(200, -50, 2));
    }

    @Test
    public void warmupTime_should_be_positive_number() {
        Assertions.assertThrows(TimeIsMinus.class,
                () -> new Car(200, 50, -2));
    }

    @Test
    public void topSpeed_should_increase_by_10_each_time_the_car_use_upgrade_topSpeed() {
        Car car = new Car(200, 4, 2);

        car.upgrade(Augmentation.TOP_SPEED);

        Assertions.assertEquals(210, car.getTopSpeed());
    }

    @Test
    public void acceleration_should_increase_by_5_each_time_the_car_use_upgrade_acceleration() {
        Car car = new Car(200, 4, 2);

        car.upgrade(Augmentation.ACCELERATIONS);

        Assertions.assertEquals(9, car.getAcceleration());
    }

    @Test
    public void warmupTime_should_decrease_by_half_each_time_the_car_use_upgrade_warmupTime() {
        Car car = new Car(200, 4, 2);

        car.upgrade(Augmentation.WARMUP_TIME);

        Assertions.assertEquals(1.5, car.getWarmUpTime());
    }

    @Test
    public void minimum_warmupTime_is_zero() {
        Car car = new Car(200, 4, 0);

        car.upgrade(Augmentation.WARMUP_TIME);

        Assertions.assertEquals(0, car.getWarmUpTime());
    }
}
