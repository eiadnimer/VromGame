package me.eiad.vromgame.rules;

import me.eiad.vromgame.Race;

@FunctionalInterface
public interface Rule {
// void
    ValidationResult isValid(Race race);
}
