package nl.ramondevaan.adventofcode.day06;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Distribution {
    private final List<MemoryBank> memoryBanks;

    public Distribution(List<MemoryBank> memoryBanks) {
        this.memoryBanks = Collections.unmodifiableList(new ArrayList<>(memoryBanks));
    }
}
