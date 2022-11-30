package nl.ramondevaan.adventofcode.day25;

import java.util.regex.Pattern;

public class StepCountParser {
  private final static Pattern STEP_COUNT_PATTERN = Pattern.compile(
      "Perform a diagnostic checksum after (?<count>\\d+) steps\\.");

  public int parse(final String line) {
    final var matcher = STEP_COUNT_PATTERN.matcher(line.trim());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return Integer.parseInt(matcher.group("count"));
  }
}
