package nl.ramondevaan.adventofcode.day24;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComponentParser {

  public Set<Component> parse(List<String> lines) {
    final var set = new HashSet<Component>(lines.size());

    int count = 0;
    for (String line : lines) {
      final var split = line.split("/");
      set.add(new Component(count++, Integer.parseInt(split[0]), Integer.parseInt(split[1])));
    }

    return Collections.unmodifiableSet(set);
  }
}
