package me.eiad.vromgame;

import lombok.Getter;
import lombok.Setter;
import me.eiad.vromgame.exeptions.AccelerationShouldBePositive;
import me.eiad.vromgame.exeptions.TimeIsMinus;
import me.eiad.vromgame.exeptions.TopSpeedShouldBePositive;

@Getter
@Setter
public class Car {
    private int topSpeed;
    private int acceleration;
    private int wormUpTime;

    public Car(int topSpeed, int acceleration, int wormUpTime) {
        validate(topSpeed, acceleration, wormUpTime);
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.wormUpTime = wormUpTime;
    }

    private void validate(int topSpeed, int acceleration, int wormUpTime) {
        if (topSpeed < 0) {
            throw new TopSpeedShouldBePositive();
        }
        if (acceleration < 0) {
            throw new AccelerationShouldBePositive();
        }
        if (wormUpTime < 0) {
            throw new TimeIsMinus();
        }
    }

}
