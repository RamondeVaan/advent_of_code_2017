package nl.ramondevaan.adventofcode.day21;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RuleExtender {

  public Stream<Rule> flatMap(final Rule originalRule) {
    final var originalPattern = originalRule.pattern();
    return Stream.iterate(originalPattern, this::rotate).limit(4)
        .flatMap(pattern -> Stream.of(pattern, flipHorizontal(pattern)))
        .map(pattern -> new Rule(pattern, originalRule.result()))
        .collect(Collectors.collectingAndThen(
            Collectors.toMap(rule -> hashCode(rule.pattern()), Function.identity(), (a, b) -> a),
            map -> map.values().stream()
        ));
  }

  private Grid flipHorizontal(final Grid pattern) {
    final var builder = ArrayGrid.builder(pattern.size());

    pattern.forEach((row, column, value) -> builder.set(pattern.size() - row - 1, column, value));

    return builder.build();
  }

  private Grid rotate(final Grid pattern) {
    final var builder = ArrayGrid.builder(pattern.size());

    pattern.forEach(((row, column, value) -> builder.set(pattern.size() - column - 1, row, value)));

    return builder.build();
  }

  private static int hashCode(final Grid grid) {
    if (grid.size() != grid.size() || grid.size() * grid.size() > 32) {
      throw new IllegalArgumentException();
    }

    var count = 0;
    int result = 0;

    for (int row = 0; row < grid.size(); row++) {
      for (int column = 0; column < grid.size(); column++) {
        if (grid.get(row, column)) {
          result |= 1 << count;
        }
        count++;
      }
    }

    return result;
  }
}
