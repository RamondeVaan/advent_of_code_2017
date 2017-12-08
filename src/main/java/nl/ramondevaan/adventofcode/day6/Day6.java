package nl.ramondevaan.adventofcode.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {
    private final List<Distribution> distributions;

    private Day6(List<Distribution> distributions) {
        this.distributions = distributions == null ?
                Collections.emptyList() :
                Collections.unmodifiableList(new ArrayList<>(distributions));
    }

    public int numberOfSteps() {
        List<Distribution> distributions = new ArrayList<>(this.distributions);
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

    public int cycleSize() {
        List<Distribution> distributions = new ArrayList<>(this.distributions);
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

    public static Day6 create(List<Distribution> distributions) {
        return new Day6(new ArrayList<>(distributions));
    }

    public static Day6 create(Path file) throws IOException {
        return new Day6(
                Files.lines(file)
                        .map(s -> Arrays.asList(s.split("\\s+")))
                        .map(a -> a.stream()
                                .map(Integer::parseInt).map(MemoryBank::new)
                                .collect(Collectors.toList()))
                        .map(Distribution::new)
                        .collect(Collectors.toList())
        );
    }
}
