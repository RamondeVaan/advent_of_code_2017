package nl.ramondevaan.adventofcode.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day3 {
    private final int value;

    private Day3(int value) {
        this.value = value;
    }

    public int spiralManhattanDistance() {
        SpiralWalker     s     = new SpiralWalker();
        final Coordinate start = new Coordinate(0, 0);

        Coordinate c = s.next().getLeft();

        for(int i = 0; i < value - 1 && s.hasNext(); i++) {
            c = s.next().getLeft();
        }

        return Coordinate.manhattanDistance(start, c);
    }

    public int stressTest() {
        SpiralSumWalker s = new SpiralSumWalker();

        int v = 0;

        while (s.hasNext() && v <= value) {
            v = s.next().getRight();
        }

        return v;
    }

    public static Day3 create(Path file) throws IOException {
        return new Day3(Files.lines(file)
                .mapToInt(Integer::parseInt)
                .findFirst()
                .orElse(1));
    }

    public static Day3 create(int value) {
        return new Day3(value);
    }
}
