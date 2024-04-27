package me.eiad.vromgame.core;

import me.eiad.vromgame.exeptions.RoundsShouldStartsSequentially;
import me.eiad.vromgame.rules.*;

import java.util.*;

public class Race {

    private final RewardSystem reward;
    private final UpgradeSystem upgrade = new Upgrade();
    private final List<Augmentation> augmentations = new ArrayList<>();
    private final Random random = new Random();
    private final List<Car> cars;
    private final int rounds;
    private final List<Track> tracks;
    private final List<Rule> rules = new ArrayList<>();
    private final Map<Integer, Map<Car, Double>> resultOFRace = new HashMap<>();
    private final Map<Integer, Map<Car, Report>> carReport = new HashMap<>();
    private Car winner;
    private int roundNumber = 1;
    private Car winnerOfRound;
    private Car loser;

    public Race(List<Car> cars, int rounds, List<Track> tracks) {
        rules.add(new NumberOfCarsValidation());
        rules.add(new RoundValidation());
        rules.add(new TrackValidation());
        rules.add(new CarsEqual());
        this.cars = cars;
        this.rounds = rounds;
        this.tracks = tracks;
        checkIfValid();
        augmentations.add(Augmentation.TOP_SPEED);
        augmentations.add(Augmentation.ACCELERATIONS);
        augmentations.add(Augmentation.WARMUP_TIME);
        reward = new Reward(rounds);
    }

    public List<Car> start() {
        Map<Car, Double> result = new HashMap<>();
        Map<Car, Report> report = new HashMap<>();
        while (rounds >= roundNumber) {
            int randomIndex = random.nextInt(tracks.size());
            Track pickedTrack = tracks.get(randomIndex);
            for (Car car : cars) {
                double carTime = car.getTime(pickedTrack.getLength());
                result.put(car, carTime);
                resultOFRace.put(roundNumber, result);
                report.put(car, car.getReport(pickedTrack.getLength()));
                carReport.put(roundNumber, report);
            }
            List<Car> winners = getWinners(roundNumber);
            reward.segregatePoints(winners, roundNumber);
            upgrade.upgrade(winners, augmentations);
            roundNumber++;
        }
        return getWinners(roundNumber - 1);
    }

    private void checkIfValid() {
        for (Rule rule : rules) {
            rule.isValid(this);
        }
    }

    public Car getLoser(int roundNumber) {
        Map<Car, Double> result = resultOFRace.get(roundNumber);
        double maximum = 0;
        for (Double carTime : result.values()) {
            if (carTime > maximum) {
                maximum = carTime;
            }
        }
        for (Map.Entry<Car, Double> entry : result.entrySet()) {
            if (Objects.equals(entry.getValue(), maximum)) {
                loser = entry.getKey();
            }
        }
        return loser;
    }

    public Car getWinner(int roundNumber) {
        Map<Car, Double> resultOfRound = resultOFRace.get(roundNumber);
        if (resultOfRound == null) {
            throw new RoundsShouldStartsSequentially();
        }
        double bestScore = Integer.MAX_VALUE;
        for (Double carTime : resultOfRound.values()) {
            if (carTime < bestScore) {
                bestScore = carTime;
            }
        }
        for (Map.Entry<Car, Double> entry : resultOfRound.entrySet()) {
            if (Objects.equals(entry.getValue(), bestScore)) {
                winnerOfRound = entry.getKey();
            }
        }
        return winnerOfRound;
    }

    public Car getWinner() {
        Map<Car, Double> lastRound = resultOFRace.get(roundNumber - 1);
        double bestScore = Collections.min(lastRound.values());
        for (Map.Entry<Car, Double> entry : lastRound.entrySet()) {
            if (Objects.equals(entry.getValue(), bestScore)) {
                winner = entry.getKey();
            }
        }
        return winner;
    }

    public List<Car> getWinners(int roundNumber) {
        Map<Car, Double> resultOfRound = resultOFRace.get(roundNumber);
        List<Car> sortedKeys = new ArrayList<>();
        List<Map.Entry<Car, Double>> entryList = new ArrayList<>(resultOfRound.entrySet());
        entryList.sort(Comparator.comparingDouble(Map.Entry::getValue));
        for (Map.Entry<Car, Double> entry : entryList) {
            if (sortedKeys.contains(entry.getKey())) {
                continue;
            }
            sortedKeys.add(entry.getKey());
        }
        if (sortedKeys.size() != 1) {
            sortedKeys.remove(sortedKeys.size() - 1);
        }
        return sortedKeys;

    }

    public List<Car> getWinners() {
        return getWinners(roundNumber - 1);
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<Integer> getRounds() {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= rounds; i++) {
            result.add(i);
        }
        return result;
    }

    public Map<Car, Report> getReport(int roundNumber) {
        return carReport.get(roundNumber);
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Augmentation> getAugmentations() {
        return augmentations;
    }
}