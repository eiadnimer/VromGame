package me.eiad.vromgame.core;

import lombok.Getter;
import me.eiad.vromgame.exeptions.AccelerationShouldBePositive;
import me.eiad.vromgame.exeptions.TimeIsMinus;
import me.eiad.vromgame.exeptions.TopSpeedShouldBePositive;

import java.util.*;
@Getter
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

    protected Report getReport(double trackLength) {
        Map<Integer, String> result = new HashMap<>();
        double distanceCovered = 0;
        double currentSpeed = 0;

        for (int second = 1; distanceCovered < trackLength; second++) {
            if (second <= warmUpTime) {
                result.put(second, "Car is still not moving");
            } else {
                if (second == warmUpTime + 1) {
                    currentSpeed = acceleration * warmUpTime;
                } else {
                    currentSpeed += acceleration;
                    if (currentSpeed > topSpeed) {
                        currentSpeed = topSpeed;
                    }
                }
                double distanceThisSecond = (currentSpeed / 3.6);
                distanceCovered += distanceThisSecond;

                if (distanceCovered > trackLength) {
                    distanceThisSecond -= (distanceCovered - trackLength);
                    distanceCovered = trackLength;
                }
                result.put(second, distanceThisSecond + " meters");
            }
        }
        return new Report(result);
    }


    protected void updatePoints(Double result) {
        upgradePoints = upgradePoints + (result * 0.65);
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