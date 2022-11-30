package nl.ramondevaan.adventofcode.day25;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class StateParser {
  private final static Pattern STATE_PATTERN = Pattern.compile("^In state (?<state>\\w):$");
  private final static Pattern IF_PATTERN = Pattern.compile("^If the current value is (?<value>[01]):$");

  private final StepParser stepParser;

  public Map<Integer, State> parse(final List<String> lines) {
    final var partitions = partition(lines);

    final var stateMap = new HashMap<Integer, State>();

    for (final var partition : partitions) {
      final var index = parseStateIndex(partition.get(0));
      final var ifMap = new HashMap<Boolean, Step>();
      for (int i = 1; i < partition.size(); i += 4) {
        final var ifValue = parseIf(partition.get(i));
        final var step = stepParser.parse(partition.subList(i + 1, i + 4));
        ifMap.put(ifValue, step);
      }
      stateMap.put(index, new State(Collections.unmodifiableMap(ifMap)));
    }

    return Collections.unmodifiableMap(stateMap);
  }

  private int parseStateIndex(final String line) {
    final var matcher = STATE_PATTERN.matcher(line.trim());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return matcher.group("state").charAt(0) - 'A';
  }

  private boolean parseIf(final String line) {
    final var matcher = IF_PATTERN.matcher(line.trim());

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return switch (matcher.group("value").charAt(0)) {
      case '0' -> false;
      case '1' -> true;
      default -> throw new IllegalArgumentException();
    };
  }

  private static List<List<String>> partition(List<String> lines) {

    List<List<String>> ret = new ArrayList<>();
    List<String> current = null;
    boolean lastLineBlank = true;

    for (String line : lines) {
      boolean isBlank = line.isBlank();
      if (isBlank) {
        if (!lastLineBlank) {
          ret.add(Collections.unmodifiableList(current));
        }
      } else {
        if (lastLineBlank) {
          current = new ArrayList<>();
        }
        current.add(line);
      }
      lastLineBlank = isBlank;
    }

    if (!lastLineBlank) {
      ret.add(Collections.unmodifiableList(current));
    }

    return Collections.unmodifiableList(ret);
  }
}
