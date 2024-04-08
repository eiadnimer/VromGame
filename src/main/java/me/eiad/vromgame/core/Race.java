package me.eiad.vromgame.core;

import lombok.Getter;
import me.eiad.vromgame.rules.*;

import java.util.*;

@Getter
public class Race {
    private final List<Car> cars;
    private final List<Round> rounds;
    private List<Track> tracks;
    private final List<Rule> rules = new ArrayList<>();
    private Map<Round, Track> randomPicks = new HashMap<>();
    private List<Car> resultOfRound = new ArrayList<>();
    private final Map<Car, Double> timeForEachCar = new HashMap<>();
    private Car winner;
    private final Map<Car, Integer> winners = new HashMap<>();
    private Track pickedTrack;
    private List<Car> losingCars = new ArrayList<>();


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
        int roundCount = 1;
        randomPicks = getRandomPick();
        for (Round round : rounds) {
            pickedTrack = randomPicks.get(round);
            resultOfRound = round.start(cars);
            winner = resultOfRound.get(0);
            winners.put(winner, roundCount);
            losingCars = getLosingCars(round);
            roundCount++;
        }
        return resultOfRound;
    }

    private void checkIfValid() {
        for (Rule rule : rules) {
            rule.isValid(this);
        }
    }

    private Map<Round, Track> getRandomPick() {
        tracks = customShuffle(tracks);
        for (int i = 0; i < rounds.size(); i++) {
            randomPicks.put(rounds.get(i), tracks.get(i));
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

    public List<Car> getLosingCars(Round round) {
        Map<Car, Double> result = round.GetDistance(cars);
        List<Double> distances = result.values().stream().toList();
        for (Double distance : distances) {
            if (distance < pickedTrack.getLength()) {
                Car car = result.entrySet().stream()
                        .filter(entry -> Objects.equals(entry.getValue(), pickedTrack.getLength()))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(null);
                losingCars.add(car);
            }
        }
        return losingCars;
    }
}
