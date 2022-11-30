package nl.ramondevaan.adventofcode.day25;

import java.util.List;
import java.util.regex.Pattern;

public class StepParser {
  private final static Pattern VALUE_PATTERN = Pattern.compile("^- Write the value (?<value>[01])\\.$");
  private final static Pattern MOVE_PATTERN = Pattern.compile("^- Move one slot to the (?<move>left|right)\\.$");
  private final static Pattern STATE_PATTERN = Pattern.compile("^- Continue with state (?<state>\\w)\\.$");

  public Step parse(final List<String> lines) {
    return new Step(
        parseValue(lines.get(0)),
        parseMove(lines.get(1)),
        parseStateIndex(lines.get(2))
    );
  }

  private boolean parseValue(final String line) {
    final var matcher = VALUE_PATTERN.matcher(line.trim());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return switch (matcher.group("value").charAt(0)) {
      case '0' -> false;
      case '1' -> true;
      default -> throw new IllegalArgumentException();
    };
  }

  private int parseMove(final String line) {
    final var matcher = MOVE_PATTERN.matcher(line.trim());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return switch (matcher.group("move")) {
      case "left" -> -1;
      case "right" -> 1;
      default -> throw new IllegalArgumentException();
    };
  }

  private int parseStateIndex(final String line) {
    final var matcher = STATE_PATTERN.matcher(line.trim());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return matcher.group("state").charAt(0) - 'A';
  }
}
