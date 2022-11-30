package nl.ramondevaan.adventofcode.day25;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Day25 {

  private final int initialStateIndex;
  private final int stepCount;
  private final Map<Integer, State> stateMap;

  public Day25(final List<String> lines) {
    final var initialStateParser = new InitialStateParser();
    final var stepCountParser = new StepCountParser();
    final var stepParser = new StepParser();
    final var stateParser = new StateParser(stepParser);

    this.initialStateIndex = initialStateParser.parse(lines.get(0));
    this.stepCount = stepCountParser.parse(lines.get(1));
    this.stateMap = stateParser.parse(lines.subList(3, lines.size()));
  }

  public int exercise1() {
    final var tape = new HashSet<Integer>();

    var state = stateMap.get(initialStateIndex);
    var cursor = 0;

    for (int i = 0; i < stepCount; i++) {
      var contains = tape.contains(cursor);
      var step = state.steps().get(contains);
      if (step.value()) {
        tape.add(cursor);
      } else {
        tape.remove(cursor);
      }
      cursor += step.move();
      state = stateMap.get(step.stateIndex());
    }
    return tape.size();
  }
}
