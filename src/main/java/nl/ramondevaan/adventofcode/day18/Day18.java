package nl.ramondevaan.adventofcode.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day18 {
    private final List<Integer> input;

    private Day18(List<Integer> input) {
        this.input = input == null ?
                     Collections.emptyList() :
                     Collections.unmodifiableList(input);
    }

    public static Day18 create(Path file) throws IOException {
        return new Day18(
                Files.lines(file)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList())
        );
    }

    public static Day18 create(List<Integer> input) {
        return new Day18(new ArrayList<>(input));
    }
}
