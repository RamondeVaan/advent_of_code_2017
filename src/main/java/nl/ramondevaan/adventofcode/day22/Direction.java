package nl.ramondevaan.adventofcode.day22;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum Direction {
  UP(0, 1),
  DOWN(0, -1),
  LEFT(-1, 0),
  RIGHT(1, 0);

  private final static Map<Direction, Direction> ROTATE_LEFT = Map.of(
      UP, LEFT,
      LEFT, DOWN,
      DOWN, RIGHT,
      RIGHT, UP
  );

  private final static Map<Direction, Direction> ROTATE_RIGHT = Map.of(
      UP, RIGHT,
      RIGHT, DOWN,
      DOWN, LEFT,
      LEFT, UP
  );

  private final static Map<Direction, Direction> REVERSE = Map.of(
      UP, DOWN,
      RIGHT, LEFT,
      DOWN, UP,
      LEFT, RIGHT
  );

  private final int xDiff;
  private final int yDiff;

  public Direction rotateLeft() {
    return ROTATE_LEFT.get(this);
  }

  public Direction rotateRight() {
    return ROTATE_RIGHT.get(this);
  }

  public Direction reverse() {
    return REVERSE.get(this);
  }
}
