package nl.ramondevaan.adventofcode.day22;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day22 {

  private final Cluster initialState;

  public Day22(final List<String> lines) {
    final var parser = new InfectedParser();

    final var infected = parser.parse(lines);
    final var carrierPosition = new Coordinate(0, 0);
    final var carrierDirection = Direction.UP;

    this.initialState = new Cluster(infected, carrierPosition, carrierDirection);
  }

  public int exercise1() {
    var isInfected = false;
    var infected = new HashSet<>(initialState.getInfected());
    var position = initialState.getCarrierPosition();
    var direction = initialState.getCarrierDirection();
    var counter = 0;

    for (int i = 0; i < 10_000; i++) {
      isInfected = infected.remove(position);
      direction = isInfected ? direction.rotateRight() : direction.rotateLeft();
      if (!isInfected) {
        infected.add(position);
        counter++;
      }
      position = new Coordinate(position.x() + direction.getXDiff(), position.y() + direction.getYDiff());
    }

    return counter;
  }

  public int exercise2() {
    State state;
    var map = new HashMap<Coordinate, State>(initialState.getInfected().size());
    initialState.getInfected().forEach(coordinate -> map.put(coordinate, State.INFECTED));
    var position = initialState.getCarrierPosition();
    var direction = initialState.getCarrierDirection();
    var counter = 0;

    for (int i = 0; i < 10_000_000; i++) {
      state = map.getOrDefault(position, State.CLEAN);
      direction = switch (state) {
        case CLEAN -> direction.rotateLeft();
        case WEAKENED -> direction;
        case INFECTED -> direction.rotateRight();
        case FLAGGED -> direction.reverse();
      };
      state = state.next();
      if (State.CLEAN.equals(state)) {
        map.remove(position);
      } else {
        map.put(position, state);
      }
      if (State.INFECTED.equals(state)) {
        counter++;
      }
      position = new Coordinate(position.x() + direction.getXDiff(), position.y() + direction.getYDiff());
    }

    return counter;
  }
}
