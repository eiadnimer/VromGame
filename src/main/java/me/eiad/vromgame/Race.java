package me.eiad.vromgame;

import lombok.Getter;
import lombok.Setter;
import me.eiad.vromgame.rules.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Race {
    private final List<Car> cars;
    private final int round;
    private final List<Track> tracks;
    private final List<Rule> rules = new ArrayList<>();

    public Race(List<Car> cars, int round, List<Track> tracks) {
        this.cars = cars;
        this.round = round;
        this.tracks = tracks;
        rules.add(new NumberOfCarsValidation());
        rules.add(new RoundValidation());
        rules.add(new TrackValidation());
    }

    public void addRule(Rule rule){
        rules.add(rule);
    }

    public ValidationResults checkIfValid() {
        ValidationResults validationResults = new ValidationResults();
        for (Rule rule : rules) {
            ValidationResult result = rule.isValid(this);
            if(!result.isValid())
                validationResults.add(result);
        }
        return validationResults;
    }

    public void start(List<Car> cars, int round, List<Track> tracks) {

    }

    private Map<Integer, Integer> roundSetUp(Round round) {
        Map<Integer, Integer> result = new HashMap<>();
        int reward = 100;
        int number = round.getRoundNumber();
        for (int i = 1; i < number; i++) {
            if (result.isEmpty()) {
                result.put(i, reward);
            }
            Integer oldReward = result.get(i);
            result.put(i, oldReward + 50);
        }
        return result;
    }
}
