package nl.ramondevaan.adventofcode.day23;

public record Constant(int value) implements Value {
  @Override
  public int getValue() {
    return value;
  }
}
