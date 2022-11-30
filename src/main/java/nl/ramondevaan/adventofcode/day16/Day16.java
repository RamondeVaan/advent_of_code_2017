package nl.ramondevaan.adventofcode.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 {
  private final static List<Program> PROGRAM_INIT;

  static {
    List<Program> tempPrograms = new ArrayList<>();

    for (char c = 'a'; c <= 'p'; c++) {
      tempPrograms.add(new Program(new Identifier(String.valueOf(c))));
    }

    PROGRAM_INIT = Collections.unmodifiableList(tempPrograms);
  }

  private final List<DanceMove> danceMoves;

  private Day16(List<DanceMove> danceMoves) {
    this.danceMoves = danceMoves == null ?
        Collections.emptyList() :
        Collections.unmodifiableList(danceMoves);
  }

  private static List<Program> executeDanceMoves(
      final List<Program> programs,
      final List<DanceMove> danceMoves
  ) {
    List<Program> temp = programs;

    for (DanceMove danceMove : danceMoves) {
      temp = Collections.unmodifiableList(
          new ArrayList<>(
              danceMove.execute(temp)
          )
      );
    }

    return temp;
  }

  private static String toString(final List<Program> programs) {
    return programs
        .stream()
        .map(Program::getId)
        .map(Identifier::getId)
        .collect(Collectors.joining());
  }

  public String programOrder() {
    return toString(
        executeDanceMoves(PROGRAM_INIT, danceMoves)
    );
  }

  public String programOrderAfterBillion() {
    final int NUM_ITER = 1000000000;
    final List<List<Program>> states = new ArrayList<>();
    states.add(PROGRAM_INIT);

    List<Program> newOrder = executeDanceMoves(PROGRAM_INIT, danceMoves);

    int cycleIndex;

    for (int i = 0; i < NUM_ITER & (cycleIndex = states.indexOf(newOrder)) < 0; i++) {
      states.add(newOrder);
      newOrder = executeDanceMoves(newOrder, danceMoves);
    }

    if (cycleIndex < 0) {
      return toString(states.get(states.size() - 1));
    }

    final int cycleSize = states.size() - cycleIndex;
    final int valueOffset = (NUM_ITER - states.size()) % cycleSize;
    final int valueIndex = cycleIndex + valueOffset;

    return toString(
        states.get(valueIndex)
    );
  }

  public static Day16 create(Path file) throws IOException {
    final DanceMoveFactory factory = new DanceMoveFactory();

    return new Day16(
        Files.readAllLines(file).stream()
            .flatMap(s -> Arrays.stream(s.split(",")))
            .map(factory::parse)
            .collect(Collectors.toList())
    );
  }

  public static Day16 create(List<DanceMove> input) {
    return new Day16(new ArrayList<>(input));
  }
}
