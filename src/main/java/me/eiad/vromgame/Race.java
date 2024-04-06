package me.eiad.vromgame;

import lombok.Getter;
import me.eiad.vromgame.rules.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Race {
    private final List<Car> cars;
    private final int numberOfRounds;
    private final List<Track> tracks;
    private final List<Rule> rules = new ArrayList<>();
    private List<Integer> rounds = new ArrayList<>();

    public Race(List<Car> cars, int round, List<Track> tracks) {
        rules.add(new NumberOfCarsValidation());
        rules.add(new RoundValidation());
        rules.add(new TrackValidation());
        this.cars = cars;
        this.numberOfRounds = round;
        this.tracks = tracks;
        checkIfValid();
    }

    public List<Car> start(List<Car> cars, int numberOfRounds, List<Track> tracks) {
        List<Car> winners = new ArrayList<>();
        rounds = roundSetUp(numberOfRounds);
        for (Integer round : rounds) {
            winners = startRound(cars, round, tracks.get(0));
        }
        return winners;
    }

    private List<Integer> roundSetUp(int round) {
        for (int i = 1; i <= round; i++) {
            rounds.add(i);
        }
        return rounds;
    }

    private void checkIfValid() {
        for (Rule rule : rules) {
            rule.isValid(this);
        }
    }

    private List<Car> startRound(List<Car> cars, int numberOfRound, Track track) {
        return cars;
    }
}
