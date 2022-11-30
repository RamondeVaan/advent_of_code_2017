package nl.ramondevaan.adventofcode.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {
  private final List<Direction> input;

  private Day11(List<Direction> input) {
    this.input = input == null ?
        Collections.emptyList() :
        Collections.unmodifiableList(input);
  }

  public int distance() {
    final Coordinate start = new Coordinate(0, 0);
    Coordinate position = start;

    for (Direction d : input) {
      position = Coordinate.add(position, d.getDirection());
    }

    return Coordinate.distance(start, position);
  }

  public int furthestDistance() {
    final Coordinate start = new Coordinate(0, 0);
    Coordinate position = start;

    int max = 0;

    for (Direction d : input) {
      position = Coordinate.add(position, d.getDirection());

      max = Math.max(max, Coordinate.distance(start, position));
    }

    return max;

  }

  public static Day11 create(Path file) throws IOException {
    return new Day11(
        Files.readAllLines(file).stream()
            .flatMap(l -> Arrays.stream(l.split(",")))
            .map(Direction::parse)
            .collect(Collectors.toList())
    );
  }

  public static Day11 create(List<Direction> input) {
    return new Day11(input);
  }
}
