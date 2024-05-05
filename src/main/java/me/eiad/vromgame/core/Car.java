package me.eiad.vromgame.core;

import lombok.Getter;

import me.eiad.vromgame.rules.car_rule.AccelerationValidation;
import me.eiad.vromgame.rules.car_rule.CarRule;
import me.eiad.vromgame.rules.car_rule.TopSpeedValidation;
import me.eiad.vromgame.rules.car_rule.WarmupTimeValidation;

import java.util.*;


@Getter
public class Car {
    private final List<CarRule> rules = new ArrayList<>();
    private int topSpeed;
    private int acceleration;
    private double warmUpTime;
    private double upgradePoints;

    public Car(int topSpeed, int acceleration, double wormUpTime) {
        rules.add(new TopSpeedValidation());
        rules.add(new AccelerationValidation());
        rules.add(new WarmupTimeValidation());
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.warmUpTime = wormUpTime;
        checkIfValid();
    }

    private void checkIfValid() {
        for (CarRule rule : rules) {
            rule.isValid(this);
        }
    }

    protected double getTime(double trackLength) {
        double timeToTopSpeed = (double) (topSpeed) / acceleration;
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

    public void upgrade(Augmentation augmentationType) {
        if (augmentationType.equals(Augmentation.TOP_SPEED)) {
            upgradeTopSpeed();
        }
        if (augmentationType.equals(Augmentation.ACCELERATIONS)) {
            upgradeAcceleration();
        }
        if (augmentationType.equals(Augmentation.WARMUP_TIME)) {
            upgradeWarmupTime();
        }
    }

    private void upgradeWarmupTime() {
        if (Augmentation.isEnough(upgradePoints, Augmentation.WARMUP_TIME)) {
            if (warmUpTime == 0 || warmUpTime == 0.5) {
                warmUpTime = warmUpTime + 0;
            } else {
                warmUpTime = warmUpTime - 0.5;
            }
        }

    }

    private void upgradeAcceleration() {
        if (Augmentation.isEnough(upgradePoints, Augmentation.ACCELERATIONS)) {
            acceleration = acceleration + 5;
        }

    }

    private void upgradeTopSpeed() {
        if (Augmentation.isEnough(upgradePoints, Augmentation.TOP_SPEED)) {
            topSpeed = topSpeed + 10;
        }
    }

    public void setUpgradePoints(double points) {
        upgradePoints = upgradePoints + points;
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