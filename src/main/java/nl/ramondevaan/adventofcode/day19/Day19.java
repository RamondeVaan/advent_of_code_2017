package nl.ramondevaan.adventofcode.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class Day19 {
  private final Collection<Connection> connections;

  private Day19(Collection<Connection> connections) {
    this.connections = connections == null ? Collections.emptyList() :
        Collections.unmodifiableCollection(connections);
  }

  private Connection getStart() {
    return connections
        .stream()
        .filter(c -> c.getIn() == null && c.getOut() != null)
        .reduce((c1, c2) -> {
          throw new IllegalArgumentException(
              "Routing diagram had multiple entry-points."
          );
        })
        .orElseThrow(() -> new IllegalArgumentException(
            "Routing diagram had no start."
        ));
  }

  public String lettersFound() {
    final Connection start = getStart();

    return start.walk()
        .map(Connection::getRepresentation)
        .filter(Character::isAlphabetic)
        .map(Character::toChars)
        .map(String::new)
        .collect(Collectors.joining());
  }

  public int stepsTaken() {
    final Connection start = getStart();

    int sum = 0;
    Coordinate coordinate = new Coordinate(start.coordinate.r - 1, start.coordinate.c);

    for (Coordinate c : start.walk().map(c -> c.coordinate).toList()) {
      sum += Coordinate.manhattanDistance(c, coordinate);
      coordinate = c;
    }

    return sum;
  }


  public static Day19 create(Path file) throws IOException {
    return new Day19(Connection.parse(
        Files.readAllLines(file).stream()
            .map(s -> s.codePoints().toArray())
            .toArray(int[][]::new)
    ));
  }

  public static Day19 create(Collection<Connection> connections) {
    return new Day19(new ArrayList<>(connections));
  }
}
