package nl.ramondevaan.adventofcode.day03;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SpiralWalker implements Iterator<ImmutablePair<Coordinate, Integer>> {
  private final Map<Coordinate, Integer> map;
  private final Map<Coordinate, Integer> immutableMap;
  private int stride;
  private int curStep;
  private Direction direction;
  private boolean incStride;
  private Coordinate nextCoordinate;
  private int nextValue;

  public SpiralWalker() {
    this(1);
  }

  public SpiralWalker(int startValue) {
    map = new HashMap<>();
    immutableMap = Collections.unmodifiableMap(map);
    stride = 1;
    curStep = 0;
    direction = Direction.RIGHT;
    incStride = false;
    nextCoordinate = new Coordinate(0, 0);
    nextValue = startValue;
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public ImmutablePair<Coordinate, Integer> next() {
    if (curStep == stride) {
      if (incStride) {
        stride++;
      }

      incStride = !incStride;
      curStep = 0;
      direction = direction.nextDirectionCounterClockWise();
    }

    curStep++;
    ImmutablePair<Coordinate, Integer> ret = new ImmutablePair<>(nextCoordinate, nextValue);
    map.put(nextCoordinate, nextValue);
    nextCoordinate = direction.move(nextCoordinate);
    nextValue = nextValue(immutableMap, ret.getLeft(), nextValue, nextCoordinate);
    return ret;
  }

  protected int nextValue(Map<Coordinate, Integer> map,
                          Coordinate previousCoordinate,
                          int previousValue,
                          Coordinate nextCoordinate) {
    return previousValue + 1;
  }
}
