package me.eiad.vromgame.core;


import me.eiad.vromgame.exeptions.LengthShouldBePositiveNumber;

public class Track {
    private final double length;

    public Track(int length) {
        if (length < 1) {
            throw new LengthShouldBePositiveNumber();
        }
        this.length = length;
    }

    public double getLength() {
        return length;
    }
}
