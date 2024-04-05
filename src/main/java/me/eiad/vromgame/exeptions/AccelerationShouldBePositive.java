package me.eiad.vromgame.exeptions;

public class AccelerationShouldBePositive extends RuntimeException {

    public AccelerationShouldBePositive(String message) {
        super(message);
    }
}
