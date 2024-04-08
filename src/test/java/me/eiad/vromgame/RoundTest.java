package me.eiad.vromgame;

import me.eiad.vromgame.core.Round;
import me.eiad.vromgame.exeptions.TimeIsMinus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class RoundTest {

    @Test
    public void round_time_should_be_positive_number(){
        Assertions.assertThrows(TimeIsMinus.class
        ,()->new Round(-25));
    }
}
