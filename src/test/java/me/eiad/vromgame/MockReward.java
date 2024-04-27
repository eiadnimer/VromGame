package me.eiad.vromgame;

import me.eiad.vromgame.core.Car;
import me.eiad.vromgame.core.RewardSystem;

import java.util.List;

public class MockReward implements RewardSystem {

    private boolean isCalled = false;

    @Override
    public void segregatePoints(List<Car> cars, int roundNumber) {
        isCalled = true;
    }

    public boolean isCalled() {
        return isCalled;
    }
}
