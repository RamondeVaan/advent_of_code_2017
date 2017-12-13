package nl.ramondevaan.adventofcode.day13;

@FunctionalInterface
public interface ScannerMover {
    Scanner move(State state, Scanner scanner);
}
