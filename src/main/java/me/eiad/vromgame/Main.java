package me.eiad.vromgame;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final DragRaceGame dragGameRace = new DragRaceGame();
    private static final List<Track> tracks = new ArrayList<>();

    public static void main(String[] args) {
        // TODO: 4/5/2024  how to handle the catch
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(200, 20, 2));
        Round round = new Round(3);
        Race race = new Race(cars, round.getRoundNumber(), tracks);
        try {
            dragGameRace.checkIfValid(race);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
