package game;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeImpl implements GameOfLife {

    @Override
    public void start(int X, int Y, List<Pair> coordinates) {

        validateCoordinates(X, Y, coordinates);

        int[][] universe = new int[X][Y];

        for (Pair coordinate : coordinates) {
            universe[coordinate.x()][coordinate.y()] = 1;
        }

        while(true) {
            printStateOfUniverse(universe, X, Y);
            universe = nextStateOfUniverse(universe, X, Y);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private static int[][] nextStateOfUniverse(int[][] universe, int X, int Y) {
        List<Pair> coordinates = new ArrayList<>();

        for (int k = 0; k < X; k++) {
            for (int l = 0; l < Y; l++) {
                int liveNeighbours = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        if ((k + m >= 0 && k + m < X) && (l + n >= 0 && l + n < Y)) {
                            liveNeighbours += universe[k + m][l + n];
                        }
                    }
                }

                liveNeighbours -= universe[k][l];

                if ((universe[k][l] == 0) && (liveNeighbours == 3)) {
                    coordinates.add(new Pair(k, l));
                }
                else if ((universe[k][l] == 1) && (liveNeighbours == 2 || liveNeighbours == 3)) {
                    coordinates.add(new Pair(k, l));
                }
            }
        }

        return createNextUniverse(X, Y, coordinates);
    }

    private static int[][] createNextUniverse(int X, int Y, List<Pair> coordinates) {
        int[][] universe = new int[X][Y];
        Pair startCoordinate = calculateStartCoordinate(X, Y, coordinates);
        Pair firstCoordinate = coordinates.get(0);

        for (Pair coordinate : coordinates) {
            universe[startCoordinate.x() + coordinate.x() - firstCoordinate.x()][startCoordinate.y() + coordinate.y() - firstCoordinate.y()] = 1;
        }

        return universe;
    }

    private static Pair calculateStartCoordinate(int X, int Y, List<Pair> coordinates) {
        int centerX = X / 2;
        int centerY = Y / 2;
        int minX = X, maxX = 0, minY = Y, maxY = 0;
        for (Pair coordinate : coordinates) {
            minX = Math.min(coordinate.x(), minX);
            maxX = Math.max(coordinate.x(), maxX);
            minY = Math.min(coordinate.x(), minX);
            maxY = Math.max(coordinate.y(), maxY);
        }
        return new Pair(centerX - (maxX - minX) / 2, centerY - (maxY - minY) / 2);
    }

    private static void printStateOfUniverse(int[][] universe, int X, int Y) {
        System.out.println(" --- State of the universe --- ");
        for (int k = 0; k < X; k++) {
            for (int l = 0; l < Y; l++) {
                if (universe[k][l] == 0)
                    System.out.printf(" %s ", '\u25A1');
                else
                    System.out.printf(" %s ", '\u25A0');
            }
            System.out.println();
        }
    }

    private static void validateCoordinates(int X, int Y, List<Pair> coordinates) {
        if ((coordinates != null && !coordinates.isEmpty()) &&
                (X < 0 || Y < 0 ||
                        coordinates.stream().anyMatch(p -> ((p.x() < 0 || p.x() >= X || p.y() < 0 || p.y() >= Y))))) {
            throw new RuntimeException("Coordinates validation exception");
        }
    }
}
