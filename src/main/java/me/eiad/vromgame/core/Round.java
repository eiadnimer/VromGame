package me.eiad.vromgame.core;

import me.eiad.vromgame.exeptions.TimeIsMinus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round {
    private final int roundTime;

    public Round(int roundTime) {
        if (roundTime < 1) {
            throw new TimeIsMinus();
        }
        this.roundTime = roundTime;
    }

    public List<Car> start(List<Car> cars) {
        return GetDistance(cars).entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).toList();
    }

    public Map<Car, Double> GetDistance(List<Car> cars) {
        Map<Car, Double> result = new HashMap<>();
        for (Car car : cars) {
            double distance = car.GetDistance(roundTime);
            result.put(car, distance);
        }
        return result;
    }
}

