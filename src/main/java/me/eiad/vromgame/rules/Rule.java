package me.eiad.vromgame.rules;
import me.eiad.vromgame.core.Race;

@FunctionalInterface
public interface Rule {
    void isValid(Race race);
}
