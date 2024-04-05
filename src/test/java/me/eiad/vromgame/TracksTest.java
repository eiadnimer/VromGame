package me.eiad.vromgame;

import me.eiad.vromgame.exeptions.LengthShouldBePositiveNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TracksTest {

    @Test
    public void length_should_be_positive_number() {
        Assertions.assertThrows(LengthShouldBePositiveNumber.class,
                () -> new Track(-110));
    }
}
