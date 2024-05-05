package me.eiad.vromgame.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Augmentation {
    TOP_SPEED(10),
    ACCELERATIONS(15),
    WARMUP_TIME(15),
    ;
    private final double price;
    private static final List<Augmentation> augmentations = new ArrayList<>();

    static {
        augmentations.add(TOP_SPEED);
        augmentations.add(ACCELERATIONS);
        augmentations.add(WARMUP_TIME);
    }

    Augmentation(double price) {
        this.price = price;
    }

    public static boolean isEnough(double upgradePoints, Augmentation augmentation) {
        return upgradePoints >= augmentation.getPrice();
    }
}
