package me.eiad.vromgame.exeptions;

public class RoundsShouldNotBeLessThanTwo extends RuntimeException {
    public RoundsShouldNotBeLessThanTwo(){
        super("rounds should be more than one round");
    }
}
