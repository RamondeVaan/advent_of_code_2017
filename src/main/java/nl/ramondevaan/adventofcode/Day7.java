package nl.ramondevaan.adventofcode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {
    private static int exercise1(List<Integer> v) {
        List<Integer> values = new ArrayList<>(v);

        return 0;
    }

    private static int exercise2(List<Integer> v) {
        List<Integer> values = new ArrayList<>(v);

        return 0;
    }

    public static void main(String[] args) {
        System.out.println("");
        if (args.length != 1) {
            System.err.println("Program requires input folder as argument");
            return;
        }

        Path input = Paths.get(args[0], "Day7.txt");
        try {
            List<Integer> values = Collections.unmodifiableList(
                    Files.lines(input)
                            .map(s -> Arrays.stream(s.split("\\s+"))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList()))
                            .findFirst()
                            .orElse(new ArrayList<>())
            );

            System.out.printf("Exercise 1: %d%n", exercise1(values));
            System.out.printf("Exercise 2: %d%n", exercise2(values));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
