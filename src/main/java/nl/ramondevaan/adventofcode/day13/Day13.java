package nl.ramondevaan.adventofcode.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {
    private final List<Integer> input;

    private Day13(List<Integer> input) {
        this.input = input == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(input);
    }

    public int exercise1() {
        return 0;
    }

    public int exercise2() {
        return 0;
    }

    public static Day13 create(Path file) throws IOException {
        return new Day13(
                Files.lines(file)
                     .flatMap(l -> Arrays.stream(l.split(",")))
                     .map(Integer::parseInt)
                     .collect(Collectors.toList())
        );
    }

    public static Day13 create(List<Integer> input) {
        return new Day13(new ArrayList<>(input));
    }
}
