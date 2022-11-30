package nl.ramondevaan.adventofcode.day03;

import java.util.Map;

public class SpiralSumWalker extends SpiralWalker {
  public SpiralSumWalker() {
  }

  public SpiralSumWalker(int startValue) {
    super(startValue);
  }

  @Override
  protected int nextValue(Map<Coordinate, Integer> map,
                          Coordinate previousCoordinate,
                          int previousValue,
                          Coordinate nextCoordinate) {
    int sum = 0;

    for (Coordinate p : Coordinate.neighbors(nextCoordinate)) {
      Integer val = map.get(p);

      if (val != null) {
        sum += val;
      }
    }

    return sum;
  }
}
