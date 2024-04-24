package me.eiad.vromgame.core;

import java.util.List;
import java.util.Random;

public class Upgrade implements UpgradeSystem {
    private final Random random = new Random();

    @Override
    public void upgrade(List<Car> winners, List<Augmentation> augmentations) {
        int randomIndex = random.nextInt(augmentations.size());
        Augmentation augmentation = augmentations.get(randomIndex);
        for (Car car : winners) {
            car.upgrade(augmentation);
        }
    }
}
