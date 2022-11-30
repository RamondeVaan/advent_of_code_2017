package nl.ramondevaan.adventofcode.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {
  private final List<Integer> values;

  private Day05(List<Integer> values) {
    this.values = values == null ?
        Collections.emptyList() :
        Collections.unmodifiableList(new ArrayList<>(values));
  }

  public int numberOfSteps() {
    List<Integer> copy = new ArrayList<>(values);
    ListWalker walker = new ListWalker((index, value) -> value + 1);
    return walker.walk(copy);
  }

  public int numberOfStepsComplex() {
    List<Integer> copy = new ArrayList<>(values);
    ListWalker walker = new ListWalker((index, value) -> value + (value >= 3 ? -1 : 1));
    return walker.walk(copy);
  }

  public static Day05 create(List<Integer> values) {
    return new Day05(new ArrayList<>(values));
  }

  public static Day05 create(Path file) throws IOException {
    return new Day05(
        Files.readAllLines(file).stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList())
    );
  }
}
