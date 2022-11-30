package nl.ramondevaan.adventofcode.day22;

public record Coordinate(int x, int y) {
  @Override
  public int hashCode() {
    if (x > Short.MAX_VALUE || x < Short.MIN_VALUE || y > Short.MAX_VALUE || y < Short.MIN_VALUE) {
      throw new IllegalArgumentException();
    }
    return (x << 16) | y;
  }
}
