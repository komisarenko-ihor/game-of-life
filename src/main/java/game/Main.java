package game;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        GameOfLife gameOfLife = new GameOfLifeImpl();

        gameOfLife.start(25, 25, List.of(
                new Pair(9, 10),
                new Pair(10, 11),
                new Pair(11, 9),
                new Pair(11, 10),
                new Pair(11, 11)
        ));
    }
}
