package nl.ramondevaan.adventofcode.day10;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class CommandService implements Iterator<List<Integer>> {
  private int skip;
  private int position;
  private int currentCommand;
  private final int rounds;
  private int currentRound;
  private List<Integer> current;
  private final List<Integer> commands;

  public CommandService(List<Integer> input, List<Integer> commands) {
    this(input, commands, 1);
  }

  public CommandService(List<Integer> input, List<Integer> commands, int rounds) {
    this.skip = 0;
    this.position = 0;
    this.currentCommand = 0;
    this.rounds = rounds;
    this.currentRound = 0;
    this.current = input == null ?
        Collections.emptyList() :
        Collections.unmodifiableList(new ArrayList<>(input));
    this.commands = commands == null ?
        Collections.emptyList() :
        Collections.unmodifiableList(new ArrayList<>(commands));
  }

  @Override
  public boolean hasNext() {
    return currentRound < rounds;
  }

  @Override
  public List<Integer> next() {
    int command = commands.get(currentCommand);
    List<Integer> newList = new ArrayList<>(current);

    IntStream.range(0, command)
        .mapToObj(i -> new ImmutablePair<>(
            i + position,
            position + command - i - 1
        ))
        .map(p -> new ImmutablePair<>(
            p.getLeft() % current.size(),
            p.getRight() % current.size()
        ))
        .forEach(p -> newList.set(p.getRight(), current.get(p.getLeft())));

    current = Collections.unmodifiableList(newList);

    position += command + skip;
    position %= current.size();
    skip++;
    currentCommand++;

    if (currentCommand >= commands.size()) {
      currentCommand = 0;
      currentRound++;
    }

    return current;
  }
}
