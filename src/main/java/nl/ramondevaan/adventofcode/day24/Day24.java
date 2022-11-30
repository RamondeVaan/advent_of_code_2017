package nl.ramondevaan.adventofcode.day24;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.*;

public class Day24 {

  private final Set<Component> components;

  public Day24(final List<String> lines) {
    final var parser = new ComponentParser();

    this.components = parser.parse(lines);
  }

  public int exercise1() {
    final var result = solve();

    return result.stream()
        .max(Comparator.comparingInt(ImmutablePair::getLeft))
        .map(ImmutablePair::getLeft)
        .orElseThrow();
  }

  public int exercise2() {
    final var result = solve();

    return result.stream()
        .max(Comparator.<ImmutablePair<Integer, List<Component>>>comparingInt(pair -> pair.getRight().size())
            .thenComparingInt(ImmutablePair::getLeft))
        .map(ImmutablePair::getLeft)
        .orElseThrow();
  }

  private List<ImmutablePair<Integer, List<Component>>> solve() {
    final var result = new ArrayList<ImmutablePair<Integer, List<Component>>>();
    solve(new LinkedList<>(), 0, 0, new LinkedList<>(components), result);
    return Collections.unmodifiableList(result);
  }

  private void solve(final Deque<Component> current, final int currentLength, final int endsWith,
                     final Deque<Component> remaining, final List<ImmutablePair<Integer, List<Component>>> result) {
    final var size = remaining.size();
    boolean found = false;

    for (int i = 0; i < size; i++) {
      final var component = remaining.removeFirst();

      final int nextEnd;

      if (component.getPort1() == endsWith) {
        nextEnd = component.getPort2();
      } else if (component.getPort2() == endsWith) {
        nextEnd = component.getPort1();
      } else {
        remaining.addLast(component);
        continue;
      }

      found = true;
      current.addLast(component);
      solve(current, currentLength + component.getLength(), nextEnd, remaining, result);
      current.removeLast();
      remaining.addLast(component);
    }

    if (!found) {
      result.add(new ImmutablePair<>(currentLength, List.copyOf(current)));
    }
  }
}
