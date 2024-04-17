package me.eiad.vromgame.core;

import lombok.Getter;
import me.eiad.vromgame.exeptions.RoundsShouldStartsSequentially;
import me.eiad.vromgame.rules.*;

import java.util.*;

@Getter
public class Race {

    private final Random random = new Random();
    private final List<Car> cars;
    private final int rounds;
    private final List<Track> tracks;
    private final List<Rule> rules = new ArrayList<>();
    private int roundNumber = 0;
    private final Map<Integer, Map<Car, Double>> resultOfRace = new HashMap<>();
    private Car loser;
    private Car winnerOfRound;
    private final List<Car> winners = new ArrayList<>();


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
        return resultOfRace.get(roundNumber);
    }

    private void startRound() {
        Map<Car, Double> resultOfRound = new HashMap<>();
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

    public Car getWinnerOfRound(int number) {
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
                winnerOfRound = entry.getKey();
            }
        }
        return winnerOfRound;
    }

    public Car getWinner() {
        Map<Car, Double> lastRound = resultOfRace.get(roundNumber);
        double bestScore = Collections.min(lastRound.values());
        for (Map.Entry<Car, Double> entry : lastRound.entrySet()) {
            if (Objects.equals(entry.getValue(), bestScore)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private List<Car> getWinners(int roundNumber) {
        return winners;
    }
}
