package me.eiad.vromgame.core;

import lombok.Getter;
import me.eiad.vromgame.exeptions.LengthShouldBePositiveNumber;
@Getter
public class Track {
    private final double length;

    public Track(int length) {
        if (length < 1) {
            throw new LengthShouldBePositiveNumber();
        }
        this.length = length;
    }
}
