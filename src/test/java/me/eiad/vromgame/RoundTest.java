package me.eiad.vromgame;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoundTest {

   @Test
    public void round_should_be_positive_number(){
       Assertions.assertThrows(RoundsShouldNotBeLessThanTwo.class,
               ()->new Round(-2));
   }

}
