package me.eiad.vromgame;

import lombok.Getter;
import lombok.Setter;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;


@Getter
@Setter
public class Round {
    private final int roundNumber;

    public Round(int roundNumber) {
        if (roundNumber<1){
            throw new RoundsShouldNotBeLessThanTwo();
        }
        this.roundNumber = roundNumber;
    }
}
