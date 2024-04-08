package me.eiad.vromgame.core;

import lombok.Getter;
import me.eiad.vromgame.rules.*;

import java.util.*;

@Getter
public class Race {
    private final List<Car> cars;
    private final List<Round> rounds;
    private final List<Track> tracks;
    private final List<Rule> rules = new ArrayList<>();
    private Map<Round, Track> randomPicks = new HashMap<>();
    private List<Car> resultOfRound = new ArrayList<>();
    private final Map<Car, Double> timeForEachCar = new HashMap<>();
    private Car winner;

    public Race(List<Car> cars, List<Round> rounds, List<Track> tracks) {
        rules.add(new NumberOfCarsValidation());
        rules.add(new RoundValidation());
        rules.add(new TrackValidation());
        this.cars = cars;
        this.rounds = rounds;
        this.tracks = tracks;
        checkIfValid();
    }

    public List<Car> start() {
        randomPicks = getRandomPick(rounds, tracks);
        for (Round round : rounds) {
            Track track = randomPicks.get(round);
            resultOfRound = startRound(track, cars);
        }
        return resultOfRound;
    }

    private void checkIfValid() {
        for (Rule rule : rules) {
            rule.isValid(this);
        }
    }

    private List<Car> startRound(Track track, List<Car> cars) {
        for (Car car : cars) {
            double finalResult = timeToFinish(car, track.getLength());
            timeForEachCar.put(car, finalResult);
        }
        List<Car> result = timeForEachCar.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).toList();
        winner = result.get(0);
        return result;
    }

    private Map<Round, Track> getRandomPick(List<Round> rounds, List<Track> tracks) {
        List<Track> shuffledTracks = customShuffle(tracks);
        for (int i = 0; i < rounds.size(); i++) {
            randomPicks.put(rounds.get(i), shuffledTracks.get(i));
        }
        return randomPicks;
    }

    private List<Track> customShuffle(List<Track> tracks) {
        List<Track> result = new ArrayList<>(tracks);
        Random random = new Random();
        for (int i = tracks.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Track temp = tracks.get(i);
            result.set(i, tracks.get(j));
            result.set(j, temp);
        }
        return result;
    }

    private double timeToFinish(Car car, int length) {
        double timeToTopSpeed = (double) car.getTopSpeed() / car.getAcceleration();
        double distanceAtTopSpeed = (double) car.getTopSpeed() * timeToTopSpeed;
        double timeAtTopSpeed = (length - distanceAtTopSpeed) / car.getTopSpeed();
        return timeToTopSpeed + timeAtTopSpeed + car.getWormUpTime();
    }
}