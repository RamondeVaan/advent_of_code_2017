package nl.ramondevaan.adventofcode.day11;

import java.util.Arrays;

public enum Direction {
  NORTH("n", new Coordinate(0, -1)),
  NORTH_EAST("ne", new Coordinate(+1, -1)),
  NORTH_WEST("nw", new Coordinate(-1, 0)),
  SOUTH("s", new Coordinate(0, 1)),
  SOUTH_EAST("se", new Coordinate(1, 0)),
  SOUTH_WEST("sw", new Coordinate(-1, 1));

  private final String representation;
  private final Coordinate direction;

  Direction(String representation, Coordinate direction) {
    this.representation = representation;
    this.direction = direction;
  }

  public String getRepresentation() {
    return representation;
  }

  public Coordinate getDirection() {
    return direction;
  }

  public static Direction parse(String s) {
    return Arrays.stream(Direction.values())
        .filter(d -> d.representation.equals(s))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(
            String.format(
                "Could not parse direction from \"%s\"",
                s
            )
        ));
  }
}
