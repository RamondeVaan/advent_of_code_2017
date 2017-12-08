package nl.ramondevaan.adventofcode.day6;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Redistributor {
    Distribution redistribute(Distribution d1) {
        Optional<ImmutablePair<Integer, MemoryBank>> max = IntStream.range(0, d1.getMemoryBanks().size())
                .mapToObj(i -> new ImmutablePair<>(i, d1.getMemoryBanks().get(i)))
                .max((o1, o2) -> new CompareToBuilder()
                        .append(o1.right.getNumberOfBlocks(), o2.right.getNumberOfBlocks())
                        .append(o2.left.intValue(), o1.left.intValue())
                        .toComparison());

        if (!max.isPresent()) {
            return d1;
        }

        int[] values    = d1.getMemoryBanks().stream().mapToInt(MemoryBank::getNumberOfBlocks).toArray();
        int   remaining = max.get().right.getNumberOfBlocks();
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
