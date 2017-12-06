package nl.ramondevaan.adventofcode;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day2 {
    private static int checksum(List<List<Integer>> input) {
        return input.stream()
                .map(i -> i.stream().collect(Collectors.summarizingInt(Integer::intValue)))
                .mapToInt(s -> s.getMax() - s.getMin())
                .sum();
    }

    private static int divisibleChecksum(List<List<Integer>> input) {
        return input.stream()
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

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Program requires input folder as argument");
            return;
        }

        Path input = Paths.get(args[0], "Day2.txt");
        try {
            List<List<Integer>> values = Files.lines(input).map(s -> s.split("\\s+"))
                    .map(s -> Arrays.stream(s)
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            System.out.printf("Exercise 1: %d%n", checksum(values));
            System.out.printf("Exercise 2: %d%n", divisibleChecksum(values));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
