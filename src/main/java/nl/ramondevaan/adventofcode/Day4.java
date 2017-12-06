package nl.ramondevaan.adventofcode;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    private static long validPassphrasesNoDuplicates(List<String[]> passphrases) {
        return passphrases.stream()
                .map(l -> IntStream.range(0, l.length - 1)
                        .boxed()
                        .flatMap(i -> IntStream.range((i + 1), l.length)
                                .mapToObj(j -> new ImmutablePair<>(i, j)))
                        .map(p -> new ImmutablePair<>(l[p.getLeft()], l[p.getRight()]))
                        .collect(Collectors.toList()))
                .filter(l -> l.stream().noneMatch(p -> p.left.equals(p.right)))
                .count();
    }

    private static long validPassphrasesNoAnagrams(List<String[]> passphrases) {
        return passphrases.stream()
                .map(l -> IntStream.range(0, l.length - 1)
                        .boxed()
                        .flatMap(i -> IntStream.range((i + 1), l.length)
                                .mapToObj(j -> new ImmutablePair<>(i, j)))
                        .map(p -> new ImmutablePair<>(l[p.getLeft()], l[p.getRight()]))
                        .collect(Collectors.toList()))
                .filter(l -> l.stream().noneMatch(p -> p.left.equals(p.right)))
                .filter(l -> l.stream().noneMatch(p -> isAnagram(p.left, p.right)))
                .count();
    }

    private static boolean isAnagram(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Program requires input folder as argument");
            return;
        }

        Path input = Paths.get(args[0], "Day4.txt");
        try {
            List<String[]> values = Files.lines(input)
                    .map(s -> s.split("\\s+"))
                    .collect(Collectors.toList());
            System.out.printf("Exercise 1: %d%n", validPassphrasesNoDuplicates(values));
            System.out.printf("Exercise 2: %d%n", validPassphrasesNoAnagrams(values));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
