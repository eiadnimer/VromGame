package me.eiad.vromgame.core;

import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;
import me.eiad.vromgame.exeptions.RoundsShouldNotBeLessThanTwo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reward {
    private final int rounds;
    public Reward(int rounds) {
        if (rounds < 2) {
            throw new RoundsShouldNotBeLessThanTwo();
        }
        this.rounds = rounds;
    }

    public void segregatePoints(List<Car> cars, int roundNumber) {
        if (cars.isEmpty()) {
            throw new CarsShouldBeMoreThanOneCar();
        }
        Map<Integer, Double> points = getPoints();
        Double result = points.get(roundNumber);
        for (Car car : cars) {
            car.updatePoints(result);
            result = result - (result * 0.65);
        }
    }

    private Map<Integer, Double> getPoints() {
        double points = 100;
        Map<Integer, Double> result = new HashMap<>();
        for (int i = 1; i <= rounds; i++) {
            result.put(i, points);
            points = points + 50;
        }
        return result;
    }
}
