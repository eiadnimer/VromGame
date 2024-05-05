package me.eiad.vromgame;

import me.eiad.vromgame.core.Augmentation;
import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.UpgradeSystem;

import java.util.List;
import java.util.Set;

public class MockUpgrade implements UpgradeSystem {
    private boolean isCalled;
    @Override
    public void upgrade(List<Car> winners, List<Augmentation> augmentations) {
        isCalled = true;
    }


    public boolean isCalled() {
        return isCalled;
    }
}
