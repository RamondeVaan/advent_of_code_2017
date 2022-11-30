package nl.ramondevaan.adventofcode.day21;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day21 {

  private final List<Rule> extendedRules;
  private final Grid startGrid;

  public Day21(final List<String> lines) {
    final var gridParser = new GridParser();
    final var parser = new RuleParser(gridParser);
    final var extender = new RuleExtender();

    List<Rule> rules = lines.stream().map(parser::parse).toList();
    extendedRules = rules.stream().flatMap(extender::flatMap).toList();
    startGrid = gridParser.parse(".#./..#/###");
  }

  public int exercise1() {
    return countOn(enhance(5));
  }

  public int exercise2() {
    return countOn(enhance(18));
  }

  private int countOn(final Grid grid) {
    final AtomicInteger counter = new AtomicInteger();

    grid.forEach((row, column, value) -> {
      if (value) {
        counter.incrementAndGet();
      }
    });

    return counter.get();
  }

  private Grid enhance(final int iterations) {
    var current = startGrid;

    for (int i = 0; i < iterations; i++) {
      current = enhance(current);
    }

    final AtomicInteger counter = new AtomicInteger();

    current.forEach((row, column, value) -> {
      if (value) {
        counter.incrementAndGet();
      }
    });

    return current;
  }

  private Grid enhance(final Grid grid) {
    final var split = split(grid);

    for (int row = 0; row < split.length; row++) {
      for (int column = 0; column < split[row].length; column++) {
        final var part = split[row][column];
        split[row][column] = extendedRules.stream()
            .filter(rule -> isApplicable(rule, part))
            .map(Rule::result)
            .findFirst().orElseThrow();
      }
    }

    return join(split);
  }

  private Grid[][] split(final Grid grid) {
    final int dim;
    if (grid.size() % 2 == 0) {
      dim = 2;
    } else if (grid.size() % 3 == 0) {
      dim = 3;
    } else {
      throw new IllegalArgumentException();
    }
    final int count = grid.size() / dim;

    final var ret = new Grid[count][count];

    for (int row = 0; row < count; row++) {
      for (int column = 0; column < count; column++) {
        ret[row][column] = new ViewGrid(grid, dim, dim * row, dim * column);
      }
    }

    return ret;
  }

  private Grid join(final Grid[][] split) {
    final var splitSize = split.length;
    final var size = split[0][0].size();

    final var builder = ArrayGrid.builder(splitSize * size);

    for (int row = 0; row < split.length; row++) {
      final int rowOffset = row * size;
      for (int column = 0; column < split[row].length; column++) {
        final int columnOffset = column * size;
        split[row][column].forEach((row1, column1, value) ->
            builder.set(row1 + rowOffset, column1 + columnOffset, value));
      }
    }

    return builder.build();
  }

  private boolean isApplicable(final Rule rule, Grid grid) {
    final var pattern = rule.pattern();

    if (pattern.size() != grid.size() || pattern.size() != grid.size()) {
      return false;
    }

    for (int row = 0; row < pattern.size(); row++) {
      for (int column = 0; column < pattern.size(); column++) {
        if (pattern.get(row, column) != grid.get(row, column)) {
          return false;
        }
      }
    }

    return true;
  }
}
