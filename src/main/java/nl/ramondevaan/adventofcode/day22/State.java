package nl.ramondevaan.adventofcode.day22;

import java.util.Map;

public enum State {
  CLEAN, WEAKENED, INFECTED, FLAGGED;

  private final static Map<State, State> NEXT = Map.of(
      CLEAN, WEAKENED,
      WEAKENED, INFECTED,
      INFECTED, FLAGGED,
      FLAGGED, CLEAN
  );

  public State next() {
    return NEXT.get(this);
  }
}
