package nl.ramondevaan.adventofcode.day21;

public class GridParser {
  private final static char ON_CHAR = '#';
  private final static char OFF_CHAR = '.';

  public Grid parse(final String part) {
    final var split = part.split("/");
    final var size = split.length;
    final var builder = ArrayGrid.builder(size);

    for (int row = 0; row < size; row++) {
      final var chars = split[row].toCharArray();
      if (chars.length != size) {
        throw new IllegalArgumentException();
      }
      for (int column = 0; column < size; column++) {
        final var value = switch (chars[column]) {
          case ON_CHAR -> true;
          case OFF_CHAR -> false;
          default -> throw new IllegalArgumentException();
        };
        builder.set(row, column, value);
      }
    }

    return builder.build();
  }
}
