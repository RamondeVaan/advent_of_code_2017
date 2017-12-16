package nl.ramondevaan.adventofcode.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day17 {
    private final List<Integer> input;

    private Day17(List<Integer> input) {
        this.input = input == null ?
                     Collections.emptyList() :
                     Collections.unmodifiableList(input);
    }

    public static Day17 create(Path file) throws IOException {
        return new Day17(
                Files.lines(file)
                     .flatMap(s -> Arrays.stream(s.split(",")))
                     .map(Integer::parseInt)
                     .collect(Collectors.toList())
        );
    }

    public static Day17 create(List<Integer> input) {
        return new Day17(new ArrayList<>(input));
    }
}
