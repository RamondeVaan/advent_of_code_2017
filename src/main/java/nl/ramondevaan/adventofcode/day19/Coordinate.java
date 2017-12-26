package nl.ramondevaan.adventofcode.day19;

import lombok.Data;

import java.util.stream.Stream;

@Data
public class Coordinate {
    public final int r;
    public final int c;

    public boolean isWithinRange(int minWidth, int maxWidth, int minHeight, int maxHeight) {
        return c >= minWidth && c < maxWidth && r >= minHeight && r < maxHeight;
    }

    public static int manhattanDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.r - c2.r) + Math.abs(c1.c - c2.c);
    }

    public static Stream<Coordinate> neighbors(Coordinate coordinate) {
        return Stream.of(
                new Coordinate(coordinate.r - 1, coordinate.c - 1),
                new Coordinate(coordinate.r - 1, coordinate.c),
                new Coordinate(coordinate.r - 1, coordinate.c + 1),
                new Coordinate(coordinate.r, coordinate.c - 1),
                new Coordinate(coordinate.r, coordinate.c + 1),
                new Coordinate(coordinate.r + 1, coordinate.c - 1),
                new Coordinate(coordinate.r + 1, coordinate.c),
                new Coordinate(coordinate.r + 1, coordinate.c + 1)
        );
    }
}
