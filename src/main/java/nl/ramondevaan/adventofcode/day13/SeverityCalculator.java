package nl.ramondevaan.adventofcode.day13;

import java.util.List;

@FunctionalInterface
public interface SeverityCalculator {
    int compute(List<State> states);
}
