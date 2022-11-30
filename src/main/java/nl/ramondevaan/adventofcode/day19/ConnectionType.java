package nl.ramondevaan.adventofcode.day19;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nl.ramondevaan.adventofcode.day19.Direction.*;

public enum ConnectionType {
  NORTH_SOUTH(Stream.of(UP, DOWN).collect(Collectors.toSet())) {
    @Override
    boolean matches(int codePoint) {
      return codePoint == '|';
    }

    @Override
    public Set<Direction> getOutDirections(Direction in) {
      return this.directions
          .stream()
          .filter(d -> d != in.opposite())
          .collect(Collectors.toSet());
    }
  },
  EAST_WEST(Stream.of(LEFT, RIGHT).collect(Collectors.toSet())) {
    @Override
    boolean matches(int codePoint) {
      return codePoint == '-';
    }

    @Override
    public Set<Direction> getOutDirections(Direction in) {
      return this.directions
          .stream()
          .filter(d -> d != in.opposite())
          .collect(Collectors.toSet());
    }
  },
  TURN(Stream.of(UP, DOWN, LEFT, RIGHT).collect(Collectors.toSet())) {
    @Override
    boolean matches(int codePoint) {
      return codePoint == '+';
    }

    @Override
    public Set<Direction> getOutDirections(Direction in) {
      return this.directions
          .stream()
          .filter(d -> d != in)
          .filter(d -> d != in.opposite())
          .collect(Collectors.toSet());
    }
  },
  LETTER(Stream.of(UP, DOWN, LEFT, RIGHT).collect(Collectors.toSet())) {
    @Override
    boolean matches(int codePoint) {
      return Character.isAlphabetic(codePoint);
    }

    @Override
    public Set<Direction> getOutDirections(Direction in) {
      return Collections.singleton(in);
    }
  },
  EMPTY(Collections.emptySet()) {
    @Override
    boolean matches(int codePoint) {
      return true;
    }

    @Override
    public Set<Direction> getOutDirections(Direction in) {
      return Collections.emptySet();
    }
  };

  public final Set<Direction> directions;

  ConnectionType(Set<Direction> directions) {
    this.directions = directions;
  }

  abstract boolean matches(int codePoint);

  public abstract Set<Direction> getOutDirections(Direction in);

  public static ConnectionType parse(final int codePoint) {
    return Arrays
        .stream(ConnectionType.values())
        .filter(c -> c.matches(codePoint))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(String.format(
            "Could not parse codePoint %d (%s)",
            codePoint,
            new String(Character.toChars(codePoint))
        )));
  }
}
