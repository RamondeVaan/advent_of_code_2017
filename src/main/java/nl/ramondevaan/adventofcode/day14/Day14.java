package nl.ramondevaan.adventofcode.day14;

import nl.ramondevaan.adventofcode.day10.Day10;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day14 {
    private final static String FORMAT = "%s-%d";
    private final static int    WIDTH  = 128;
    private final static int    HEIGHT = 128;

    private final String input;

    private Day14(String input) {
        this.input = input;
    }

    public int usedSquares() {
        return IntStream.range(0, 128)
                        .mapToObj(i -> String.format(FORMAT, input, i))
                        .map(s -> Day10.create(s).fullHash())
                        .flatMapToInt(CharSequence::chars)
                        .map(c -> Character.digit(c, 16))
                        .map(Integer::bitCount)
                        .sum();
    }

    public int numberOfGroups() {
        final int[][] disk = IntStream
                .range(0, 128)
                .mapToObj(i -> String.format(FORMAT, input, i))
                .map(s -> Day10.create(s).fullHash())
                .map(s -> s.chars()
                           .map(i -> Character.digit(i, 16))
                           .boxed()
                           .collect(Collectors.toList()))
                .map(l -> l.stream()
                           .flatMap(h -> Stream.of(
                                   (h & 0b1000) > 0,
                                   (h & 0b0100) > 0,
                                   (h & 0b0010) > 0,
                                   (h & 0b0001) > 0
                           ))
                           .mapToInt(b -> b ? 1 : 0)
                           .toArray())
                .toArray(int[][]::new);

        final int[][]       groups = new int[disk.length][disk[0].length];
        final AtomicInteger count  = new AtomicInteger(0);

        coordinates(WIDTH, HEIGHT)
                .filter(p -> disk[p.getLeft()][p.getRight()] > 0)
                .filter(p -> groups[p.getLeft()][p.getRight()] == 0)
                .forEach(p -> setGroup(groups, disk, p, neighbors(p, WIDTH, HEIGHT)
                        .mapToInt(r -> groups[r.getLeft()][r.getRight()])
                        .filter(i -> i > 0)
                        .max()
                        .orElseGet(count::incrementAndGet)));

        return count.get();
    }

    private static Stream<ImmutablePair<Integer, Integer>>
    neighbors(ImmutablePair<Integer, Integer> coordinate, int width, int height) {
        return Stream.of(
                new ImmutablePair<>(coordinate.getLeft() + 1, coordinate.getRight()),
                new ImmutablePair<>(coordinate.getLeft() - 1, coordinate.getRight()),
                new ImmutablePair<>(coordinate.getLeft(), coordinate.getRight() + 1),
                new ImmutablePair<>(coordinate.getLeft(), coordinate.getRight() - 1)
        )
                     .filter(c -> c.getLeft() >= 0)
                     .filter(c -> c.getLeft() < width)
                     .filter(c -> c.getRight() >= 0)
                     .filter(c -> c.getRight() < height);
    }

    private static Stream<ImmutablePair<Integer, Integer>>
    coordinates(int width, int height) {
        return IntStream.range(0, width)
                        .mapToObj(i -> new ImmutablePair<>(i, 0))
                        .flatMap(p -> IntStream.range(0, height)
                                               .mapToObj(i -> new ImmutablePair<>(p.getLeft(), i)));
    }

    private static Stream<ImmutablePair<Integer, Integer>>
    rowCoordinates(int row, int width) {
        return IntStream.range(0, width)
                        .mapToObj(i -> new ImmutablePair<>(row, i));
    }

    private static void setGroup(int[][] groups,
                                 int[][] disk,
                                 ImmutablePair<Integer, Integer> coordinate,
                                 int group) {
        groups[coordinate.getLeft()][coordinate.getRight()] = group;

        neighbors(coordinate, groups[0].length, groups.length)
                .filter(p -> disk[p.getLeft()][p.getRight()] > 0)
                .filter(p -> groups[p.getLeft()][p.getRight()] == 0)
                .forEach(p -> setGroup(groups, disk, p, group));
    }

    @SuppressWarnings("unused")
    private static String Array2dToString(int[][] array) {
        int maxLength = coordinates(array[0].length, array.length)
                .mapToInt(p -> array[p.getLeft()][p.getRight()])
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(1);

        final String formatString = "%0" + maxLength + "d";

        return IntStream.range(0, array.length)
                        .mapToObj(i -> rowCoordinates(i, array[0].length)
                                .mapToInt(p -> array[p.getLeft()][p.getRight()])
                                .mapToObj(j -> String.format(formatString, j))
                                .collect(Collectors.joining(" ")))
                        .collect(Collectors.joining("%n"));
    }

    public static Day14 create(Path file) throws IOException {
        return new Day14(
                Files.lines(file)
                     .collect(Collectors.joining())
        );
    }

    public static Day14 create(String input) {
        return new Day14(input);
    }
}
