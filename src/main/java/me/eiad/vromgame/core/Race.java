package me.eiad.vromgame.core;

import lombok.Getter;
import me.eiad.vromgame.exeptions.RoundsShouldStartsSequentially;
import me.eiad.vromgame.rules.*;

import java.util.*;

@Getter
public class Race {

    // remove result of round
    // carTime should not be saved
    private final Random random = new Random();
    private final List<Car> cars;
    private final int rounds;
    private final List<Track> tracks;
    private final List<Rule> rules = new ArrayList<>();
    private int roundNumber = 0;
    private final Map<Car, Double> resultOfRound = new HashMap<>();
    private final Map<Integer, Map<Car, Double>> resultOfRace = new HashMap<>();
    private Car loser;
    private Car winner;

    public Race(List<Car> cars, int rounds, List<Track> tracks) {
        rules.add(new NumberOfCarsValidation());
        rules.add(new RoundValidation());
        rules.add(new TrackValidation());
        rules.add(new CarsEqual());
        this.cars = cars;
        this.rounds = rounds;
        this.tracks = tracks;
        checkIfValid();
    }

    public Map<Car, Double> start() {
        startRound();
        return resultOfRound;
    }

    private void startRound() {
        int randomIndex = random.nextInt(tracks.size());
        Track pickedTrack = tracks.get(randomIndex);
        for (Car car : cars) {
            double carTime = car.getTime(pickedTrack.getLength());
            roundNumber++;
            resultOfRound.put(car, carTime);
            resultOfRace.put(roundNumber, resultOfRound);
        }
    }

    private void checkIfValid() {
        for (Rule rule : rules) {
            rule.isValid(this);
        }
    }

    public Car getLoser(int roundNumber) {
        Map<Car, Double> resultOfRound = resultOfRace.get(roundNumber);
        double maximum = 0;
        for (Double carTime : resultOfRound.values()) {
            if (carTime > maximum) {
                maximum = carTime;
            }
        }
        for (Map.Entry<Car, Double> entry : resultOfRound.entrySet()) {
            if (Objects.equals(entry.getValue(), maximum)) {
                loser = entry.getKey();
            }
        }
        return loser;
    }

    public Car getWinner(int number) {
        if (number > roundNumber) {
            throw new RoundsShouldStartsSequentially();
        }
        Map<Car, Double> resultOfRound = resultOfRace.get(number);
        double minimum = Integer.MAX_VALUE;
        for (Double carTime : resultOfRound.values()) {
            if (carTime < minimum) {
                minimum = carTime;
            }
        }
        for (Map.Entry<Car, Double> entry : resultOfRound.entrySet()) {
            if (Objects.equals(entry.getValue(), minimum)) {
                winner = entry.getKey();
            }
        }
        return winner;
    }
}
