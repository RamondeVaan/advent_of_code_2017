package nl.ramondevaan.adventofcode.day4;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
    private final List<String[]> passphrases;

    private Day4(List<String[]> passphrases) {
        this.passphrases = passphrases == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(new ArrayList<>(passphrases));
    }

    public long validPassphrasesNoDuplicates() {
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

    public long validPassphrasesNoAnagrams() {
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

    public static Day4 create(List<String[]> passphrases) {
        List<String[]> temp = new ArrayList<>();

        for (String[] passphrase : passphrases) {
            temp.add(Arrays.copyOf(passphrase, passphrase.length));
        }

        return new Day4(temp);
    }

    public static Day4 create(Path file) throws IOException {
        return new Day4(
                Files.lines(file)
                        .map(s -> s.split("\\s+"))
                        .collect(Collectors.toList())
        );

    }
}
