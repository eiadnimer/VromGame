package me.eiad.vromgame.rules.race_rule;
import me.eiad.vromgame.core.Race;

@FunctionalInterface
public interface RaceRule {
    void isValid(Race race);
}
