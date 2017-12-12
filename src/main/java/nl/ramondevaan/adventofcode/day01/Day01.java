package nl.ramondevaan.adventofcode.day01;

import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day01 {
    @Getter
    private final List<Integer> values;

    private Day01(List<Integer> values) {
        this.values = values == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(new ArrayList<>(values));
    }

    public int sumIfPairs(int spacer) {
        return IntStream.range(0, values.size())
                .mapToObj(i -> new ImmutablePair<>(i, (i + spacer) % values.size()))
                .map(p -> new ImmutablePair<>(values.get(p.left), values.get(p.right)))
                .filter(p -> p.left.equals(p.right))
                .mapToInt(ImmutablePair::getLeft)
                .sum();
    }

    public static Day01 create(Path path) throws IOException {
        return new Day01(Files.lines(path).flatMapToInt(CharSequence::chars)
                              .map(Character::getNumericValue)
                              .boxed()
                              .collect(Collectors.toList()));
    }

    public static Day01 create(List<Integer> values) {
        return new Day01(values);
    }
}
