package me.eiad.vromgame.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reward {

    private final int rounds;

    public Reward(int rounds) {
        this.rounds = rounds;
    }

    public void segregatePoints(List<Car> cars, int roundNumber) {
        Map<Integer, Double> points = getPoints();
        Double result = points.get(roundNumber);
        for (Car car : cars) {
            double upgradePoints = car.getUpgradePoints();
            car.setUpgradePoints(upgradePoints + (result * 0.65));
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
