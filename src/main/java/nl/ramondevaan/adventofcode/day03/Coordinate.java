package nl.ramondevaan.adventofcode.day03;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Coordinate {
    public final int x;
    public final int y;

    public static int manhattanDistance(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
    }

    public static List<Coordinate> neighbors(Coordinate c) {
        return Arrays.asList(
                new Coordinate(c.x - 1, c.y - 1),
                new Coordinate(c.x - 1, c.y),
                new Coordinate(c.x - 1, c.y + 1),
                new Coordinate(c.x, c.y - 1),
                new Coordinate(c.x, c.y + 1),
                new Coordinate(c.x + 1, c.y - 1),
                new Coordinate(c.x + 1, c.y),
                new Coordinate(c.x + 1, c.y + 1)
        );
    }
}
