package nl.ramondevaan.adventofcode.day25;

import java.util.regex.Pattern;

public class InitialStateParser {
  private final static Pattern INITIAL_STATE_PATTERN = Pattern.compile("Begin in state (?<state>\\w)\\.");

  public int parse(final String line) {
    final var matcher = INITIAL_STATE_PATTERN.matcher(line.trim());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return matcher.group("state").charAt(0) - 'A';
  }
}
