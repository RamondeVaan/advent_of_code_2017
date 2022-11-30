package nl.ramondevaan.adventofcode.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day17 {
  private final int steps;

  private Day17(int steps) {
    this.steps = steps;
  }

  public int valueAfter2017() {
    final int iterations = 2017;

    int[] values = new int[iterations + 1];

    int index = 0;

    for (int i = 1; i <= iterations; i++) {
      index = ((index + steps) % i) + 1;
      System.arraycopy(values, index, values, index + 1, i - index);
      values[index] = i;
    }

    return values[(index + 1) % values.length];
  }

  public int valueAfter0() {
    final int iterations = 50000000;

    int index = 0;
    int valueAfter0 = -1;

    for (int i = 1; i <= iterations; i++) {
      index = ((index + steps) % i) + 1;
      if (index == 1) {
        valueAfter0 = i;
      }
    }

    return valueAfter0;
  }

  public static Day17 create(Path file) throws IOException {
    return new Day17(
        Files.readAllLines(file).stream()
            .map(Integer::parseInt)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format(
                "Could not parse a number from \"%s\"",
                file)))
    );
  }

  public static Day17 create(int input) {
    return new Day17(input);
  }
}
