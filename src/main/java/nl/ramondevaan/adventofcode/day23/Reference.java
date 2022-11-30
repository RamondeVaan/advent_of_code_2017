package nl.ramondevaan.adventofcode.day23;

public record Reference(int index) implements Value {
  @Override
  public int getValue() {
    return index;
  }
}
