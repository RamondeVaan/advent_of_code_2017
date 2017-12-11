package nl.ramondevaan.adventofcode.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    private final List<Integer> input;

    private Day12(List<Integer> input) {
        this.input = input == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(input);
    }


    public static Day12 create(Path file) throws IOException {
        return new Day12(
                Files.lines(file)
                     .flatMap(l -> Arrays.stream(l.split(",")))
                     .map(Integer::parseInt)
                     .collect(Collectors.toList())
        );
    }

    public static Day12 create(List<Integer> input) {
        return new Day12(input);
    }
}
