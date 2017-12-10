package nl.ramondevaan.adventofcode.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Day9 {
    private final String input;

    private Day9(String input) {
        this.input = input;
    }

    private void parse() {

    }

    public int groupScore() {
        return 0;
    }

    public static Day9 create(Path file) throws IOException {
        return new Day9(Files.lines(file).collect(Collectors.joining()));
    }

    public static Day9 create(String input) {
        return new Day9(input);
    }
}
