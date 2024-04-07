package me.eiad.vromgame.core;

import lombok.Getter;
import lombok.Setter;
import me.eiad.vromgame.exeptions.AccelerationShouldBePositive;
import me.eiad.vromgame.exeptions.TimeIsMinus;
import me.eiad.vromgame.exeptions.TopSpeedShouldBePositive;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return topSpeed == car.topSpeed && acceleration == car.acceleration && wormUpTime == car.wormUpTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topSpeed, acceleration, wormUpTime);
    }
}
