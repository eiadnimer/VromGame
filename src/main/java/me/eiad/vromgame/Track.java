package me.eiad.vromgame;

import lombok.Getter;
import me.eiad.vromgame.exeptions.LengthShouldBePositiveNumber;
@Getter
public class Track {
    private final int length;

    public Track(int length) {
        if (length < 1) {
            throw new LengthShouldBePositiveNumber();
        }
        this.length = length;
    }
}
