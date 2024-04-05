package me.eiad.vromgame.exeptions;

public class TopSpeedShouldBePositive extends RuntimeException {

    public TopSpeedShouldBePositive(String message) {
        super(message);
    }
}
