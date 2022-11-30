package nl.ramondevaan.adventofcode.day11;

import lombok.Data;

import static java.lang.Math.abs;

@Data
public class Coordinate {
  public final int q;
  public final int r;

  public static Coordinate add(Coordinate c1, Coordinate c2) {
    return new Coordinate(c1.q + c2.q, c1.r + c2.r);
  }

  public static Coordinate subtract(Coordinate c1, Coordinate c2) {
    return new Coordinate(c1.q - c2.q, c1.r - c2.r);
  }

  public static int distance(Coordinate a, Coordinate b) {
    return (abs(a.q - b.q)
        + abs(a.q + a.r - b.q - b.r)
        + abs(a.r - b.r)) / 2;
  }
}
