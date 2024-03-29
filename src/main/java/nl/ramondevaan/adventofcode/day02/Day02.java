package nl.ramondevaan.adventofcode.day02;

import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day02 {
  @Getter
  private final List<List<Integer>> values;

  private Day02(List<List<Integer>> values) {
    this.values = values == null ? Collections.emptyList() :
        Collections.unmodifiableList(values);
  }

  public int checksum() {
    return values.stream()
        .map(i -> i.stream().collect(Collectors.summarizingInt(Integer::intValue)))
        .mapToInt(s -> s.getMax() - s.getMin())
        .sum();
  }

  public int divisibleChecksum() {
    return values.stream()
        .mapToInt(l -> IntStream.range(0, l.size() - 1)
            .boxed()
            .flatMap(i -> IntStream.range((i + 1), l.size())
                .mapToObj(j -> new ImmutablePair<>(i, j)))
            .map(p -> new ImmutablePair<>(l.get(p.getLeft()), l.get(p.getRight())))
            .map(p -> p.left >= p.right ? p : new ImmutablePair<>(p.right, p.left))
            .filter(p -> p.left % p.right == 0)
            .mapToInt(p -> p.left / p.right)
            .findAny()
            .orElse(0))
        .sum();
  }

  public static Day02 create(Path file) throws IOException {
    return new Day02(
        Files.readAllLines(file).stream()
            .map(s -> s.split("\\s+"))
            .map(s -> Arrays.stream(s)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList()))
            .collect(Collectors.toList())
    );
  }

  public static Day02 create(List<List<Integer>> values) {
    List<List<Integer>> temp = new ArrayList<>();

    values.forEach(v -> temp.add(Collections.unmodifiableList(new ArrayList<>(v))));

    return new Day02(temp);
  }
}
