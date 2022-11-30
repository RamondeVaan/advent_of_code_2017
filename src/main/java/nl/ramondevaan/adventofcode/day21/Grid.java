package nl.ramondevaan.adventofcode.day21;

public interface Grid {
  boolean get(int row, int column);

  int size();

  void forEach(final GridValueConsumer consumer);
}
