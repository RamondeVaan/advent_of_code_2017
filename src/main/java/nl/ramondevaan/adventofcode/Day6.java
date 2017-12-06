package nl.ramondevaan.adventofcode;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {
    private static class MemoryBank {
        private final int numberOfBlocks;

        private MemoryBank(int numberOfBlocks) {
            this.numberOfBlocks = numberOfBlocks;
        }

        public int getNumberOfBlocks() {
            return numberOfBlocks;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MemoryBank that = (MemoryBank) o;
            return numberOfBlocks == that.numberOfBlocks;
        }

        @Override
        public int hashCode() {
            return Objects.hash(numberOfBlocks);
        }
    }

    private static class Distribution {
        private final List<MemoryBank> memoryBanks;

        private Distribution(List<MemoryBank> memoryBanks) {
            this.memoryBanks = Collections.unmodifiableList(new ArrayList<>(memoryBanks));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Distribution that = (Distribution) o;
            return Objects.equals(memoryBanks, that.memoryBanks);
        }

        @Override
        public int hashCode() {
            return Objects.hash(memoryBanks);
        }
    }

    private static class Redistributor {
        Distribution redistribute(Distribution d1) {
            Optional<ImmutablePair<Integer, MemoryBank>> max = IntStream.range(0, d1.memoryBanks.size())
                    .mapToObj(i -> new ImmutablePair<>(i, d1.memoryBanks.get(i)))
                    .max((o1, o2) -> new CompareToBuilder()
                            .append(o1.right.numberOfBlocks, o2.right.numberOfBlocks)
                            .append(o2.left.intValue(), o1.left.intValue())
                            .toComparison());

            if (!max.isPresent()) {
                return d1;
            }

            int[] values      = d1.memoryBanks.stream().mapToInt(MemoryBank::getNumberOfBlocks).toArray();
            int   remaining = max.get().right.numberOfBlocks;
            values[max.get().left] = 0;

            for (int index = (max.get().left + 1) % values.length;
                 remaining > 0;
                 index = (index + 1) % values.length, remaining--) {
                values[index] += 1;
            }

            return new Distribution(Arrays.stream(values)
                                            .mapToObj(MemoryBank::new)
                                            .collect(Collectors.toList()));
        }
    }

    private static int numberOfSteps(List<Distribution> d) {
        List<Distribution> distributions = new ArrayList<>(d);
        Redistributor redistributor = new Redistributor();

        int count = 1;

        Distribution newDist = redistributor.redistribute(distributions.get(distributions.size() - 1));

        while (!distributions.contains(newDist)) {
            distributions.add(newDist);
            newDist = redistributor.redistribute(newDist);
            count++;
        }

        return count;
    }

    private static int cycleSize(List<Distribution> d) {
        List<Distribution> distributions = new ArrayList<>(d);
        Redistributor redistributor = new Redistributor();

        Distribution newDist = redistributor.redistribute(distributions.get(distributions.size() - 1));
        int index;
        int count = 1;

        while ((index = distributions.indexOf(newDist)) < 0) {
            distributions.add(newDist);
            newDist = redistributor.redistribute(newDist);
            count++;
        }

        return count - index;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Program requires input folder as argument");
            return;
        }

        Path input = Paths.get(args[0], "Day6.txt");
        try {
            List<Distribution> distributions = Collections.unmodifiableList(Files.lines(input)
                    .map(s -> Arrays.asList(s.split("\\s+")))
                    .map(a -> a.stream()
                            .map(Integer::parseInt).map(MemoryBank::new)
                            .collect(Collectors.toList()))
                    .map(Distribution::new)
                    .collect(Collectors.toList()));

            System.out.printf("Exercise 1: %d%n", numberOfSteps(distributions));
            System.out.printf("Exercise 2: %d%n", cycleSize(distributions));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
