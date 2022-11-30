package nl.ramondevaan.adventofcode.day22;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InfectedParser {

  private final static char INFECTED = '#';
  private final static char CLEAN = '.';


  public Set<Coordinate> parse(final List<String> lines) {
    final int xOffset = lines.get(0).length() / 2;
    final int yOffset = lines.size() / 2;

    final var infectedCoordinates = new HashSet<Coordinate>();

    for (int y = 0; y < lines.size(); y++) {
      final var line = lines.get(y).toCharArray();
      for (int x = 0; x < line.length; x++) {
        boolean infected = switch (line[x]) {
          case INFECTED -> true;
          case CLEAN -> false;
          default -> throw new IllegalArgumentException();
        };
        if (infected) {
          infectedCoordinates.add(new Coordinate(x - xOffset, yOffset - y));
        }
      }
    }

    return infectedCoordinates;
  }
}
