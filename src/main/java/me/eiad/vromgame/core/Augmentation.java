package me.eiad.vromgame.core;

import lombok.Getter;
@Getter
public enum Augmentation {
    TOP_SPEED(50),
    ACCELERATIONS(75),
    WARMUP_TIME(30),
    ;
    private final double price;

    Augmentation(double price) {
        this.price = price;
    }
}
