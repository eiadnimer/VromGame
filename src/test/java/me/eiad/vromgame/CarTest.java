package me.eiad.vromgame;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.exeptions.AccelerationShouldBePositive;
import me.eiad.vromgame.exeptions.TimeIsMinus;
import me.eiad.vromgame.exeptions.TopSpeedShouldBePositive;
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
}
