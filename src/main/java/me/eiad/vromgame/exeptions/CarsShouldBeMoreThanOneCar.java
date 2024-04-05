package me.eiad.vromgame.exeptions;

public class CarsShouldBeMoreThanOneCar extends RuntimeException {

    public CarsShouldBeMoreThanOneCar() {
        super("number of cars should be more than one car");
    }
}
