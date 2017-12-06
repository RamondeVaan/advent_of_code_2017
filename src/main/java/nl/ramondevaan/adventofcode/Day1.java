package nl.ramondevaan.adventofcode;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day1 {
    public static int sumIfPairs(List<Integer> values, int spacer) {
        return IntStream.range(0, values.size())
                .mapToObj(i -> new ImmutablePair<>(i, (i + spacer) % values.size()))
                .map(p -> new ImmutablePair<>(values.get(p.left), values.get(p.right)))
                .filter(p -> p.left.equals(p.right))
                .mapToInt(ImmutablePair::getLeft)
                .sum();
    }

    public static void main(String[] args) {
        Path input = Paths.get("D:\\Projects\\adventofcode\\input\\Day1.txt");
        try {
            List<Integer> values = Files.lines(input).flatMapToInt(CharSequence::chars)
                    .map(Character::getNumericValue)
                    .boxed()
                    .collect(Collectors.toList());
            System.out.printf("Exercise 1: %d%n", sumIfPairs(values, 1));
            System.out.printf("Exercise 2: %d%n", sumIfPairs(values, values.size() / 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
