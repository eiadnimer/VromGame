package me.eiad.vromgame;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.Round;
import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.TimeIsMinus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoundTest {

    private final Round round = new Round(5);

    @Test
    public void round_time_should_be_positive_number() {
        Assertions.assertThrows(TimeIsMinus.class
                , () -> new Round(-25));
    }

    @Test
    public void if_cars_equal_to_null_must_fail() {
        Assertions.assertThrows(CarsShouldBeMoreThanOneCar.class,
                () -> round.start(null));
    }
}
