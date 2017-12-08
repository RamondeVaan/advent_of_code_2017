package nl.ramondevaan.adventofcode.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {
    private final List<Integer> values;

    private Day5(List<Integer> values) {
        this.values = values == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(new ArrayList<>(values));
    }

    public int numberOfSteps() {
        List<Integer> copy   = new ArrayList<>(values);
        ListWalker    walker = new ListWalker((index, value) -> value + 1);
        return walker.walk(copy);
    }

    public int numberOfStepsComplex() {
        List<Integer> copy   = new ArrayList<>(values);
        ListWalker    walker = new ListWalker((index, value) -> value + (value >= 3 ? -1 : 1));
        return walker.walk(copy);
    }

    public static Day5 create(List<Integer> values) {
        return new Day5(new ArrayList<>(values));
    }

    public static Day5 create(Path file) throws IOException {
        return new Day5(
                Files.lines(file)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
    }
}
