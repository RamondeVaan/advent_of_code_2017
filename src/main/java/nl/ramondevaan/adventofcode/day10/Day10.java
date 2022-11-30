package nl.ramondevaan.adventofcode.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 {
  private final String input;

  private Day10(String input) {
    this.input = input;
  }

  private static List<Integer> executeCommands(List<Integer> input, int rounds) {
    List<Integer> values = IntStream.range(0, 256)
        .boxed()
        .collect(Collectors.toList());

    CommandService c = new CommandService(values, input, rounds);

    while (c.hasNext()) {
      values = c.next();
    }

    return values;
  }

  public int multiplyFirstTwoNumbers() {
    List<Integer> values = Stream.of(input).flatMap(l -> Arrays.stream(l.split(",")))
        .map(String::trim)
        .mapToInt(Integer::parseInt)
        .boxed()
        .collect(Collectors.toList());

    List<Integer> output = executeCommands(values, 1);

    if (output == null || output.size() < 2) {
      return 0;
    }

    return output.get(0) * output.get(1);
  }

  public String fullHash() {
    List<Integer> values = IntStream.range(0, input.length())
        .map(input::charAt)
        .boxed()
        .collect(Collectors.toList());

    values.addAll(Arrays.asList(17, 31, 73, 47, 23));

    List<Integer> output = executeCommands(values, 64);

    return IntStream.range(0, 16)
        .mapToObj(i -> IntStream.range(16 * i, 16 * (i + 1))
            .mapToObj(output::get)
            .collect(Collectors.toList())
        )
        .mapToInt(l -> l.stream()
            .mapToInt(Integer::intValue)
            .reduce((i1, i2) -> i1 ^ i2)
            .orElse(0)
        )
        .boxed()
        .map(i -> String.format("%02X", i & 0x0000FF))
        .collect(Collectors.joining());
  }

  public static Day10 create(Path file) throws IOException {
    return new Day10(String.join("", Files.readAllLines(file)));
  }

  public static Day10 create(String input) {
    return new Day10(input);
  }
}
