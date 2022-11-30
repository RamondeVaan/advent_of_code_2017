package nl.ramondevaan.adventofcode.day21;

@FunctionalInterface
public interface GridValueConsumer {

  void accept(int row, int column, boolean value);
}
