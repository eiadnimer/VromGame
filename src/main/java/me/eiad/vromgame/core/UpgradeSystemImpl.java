package me.eiad.vromgame.core;

import me.eiad.vromgame.exeptions.CarsShouldBeMoreThanOneCar;

import java.util.List;
import java.util.Random;

public class UpgradeSystemImpl implements UpgradeSystem {

    @Override
    public void upgrade(List<Car> winners, List<Augmentation> augmentations) {
        Random random = new Random();
        if (winners == null || winners.isEmpty()) {
            throw new CarsShouldBeMoreThanOneCar();
        }
        int randomIndex = random.nextInt(augmentations.size());
        Augmentation augmentation = augmentations.get(randomIndex);
        for (Car car : winners) {
            car.upgrade(augmentation);
        }
    }
}