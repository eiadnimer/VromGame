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
    private double warmUpTime;
    private double upgradePoints;

    public Car(int topSpeed, int acceleration, double wormUpTime) {
        validate(topSpeed, acceleration, wormUpTime);
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.warmUpTime = wormUpTime;
    }

    private void validate(int topSpeed, int acceleration, double warmUpTime) {
        if (topSpeed < 0) {
            throw new TopSpeedShouldBePositive();
        }
        if (acceleration < 0) {
            throw new AccelerationShouldBePositive();
        }
        if (warmUpTime < 0) {
            throw new TimeIsMinus();
        }
    }

    protected double getTime(double trackLength) {
        double timeToTopSpeed = (topSpeed - 0) / acceleration;
        double distanceDuringAcceleration = 0.5 * acceleration * timeToTopSpeed * timeToTopSpeed;
        double distanceRemaining = trackLength - distanceDuringAcceleration;
        double timeAtTopSpeed = distanceRemaining / topSpeed;
        return timeToTopSpeed + timeAtTopSpeed + warmUpTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return topSpeed == car.topSpeed && acceleration == car.acceleration && warmUpTime == car.warmUpTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topSpeed, acceleration, warmUpTime);
    }
}