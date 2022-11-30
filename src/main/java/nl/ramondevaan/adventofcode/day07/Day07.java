package nl.ramondevaan.adventofcode.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day07 {
  private final static String CHILDREN_TAG = "children";
  private final static String WEIGHT_TAG = "weight";
  private final static String NAME_TAG = "name";
  private final static String PROGRAM_REGEX =
      "^(?<" + NAME_TAG + ">.+) \\((?<" + WEIGHT_TAG + ">\\d+)\\)( ->(?<" + CHILDREN_TAG + ">.+))?$";
  private final static Pattern PROGRAM_PATTERN = Pattern.compile(PROGRAM_REGEX);

  private final Collection<Program> programs;

  private Day07(Collection<Program> programs) {
    this.programs = programs == null ?
        Collections.emptyList() :
        Collections.unmodifiableCollection(new ArrayList<>(programs));
  }

  public String rootName() {
    return programs.stream()
        .filter(p -> p.getParent() == null)
        .map(Program::getName)
        .findFirst()
        .orElse(null);
  }

  public int unbalanced() {
    Program unbalanced = programs.stream()
        .filter(p -> p.getParent() != null)
        .filter(p -> p.getSiblings().stream()
            .allMatch(q -> q.getTotalWeight() != p.getTotalWeight()))
        .filter(p -> p.getChildren().stream()
            .map(Program::getTotalWeight)
            .distinct()
            .count() < 2)
        .findFirst()
        .orElse(null);

    if (unbalanced == null) {
      return 0;
    }

    int weightDiff = unbalanced.getTotalWeight() - Stream.of(unbalanced)
        .flatMap(p -> p.getSiblings().stream())
        .mapToInt(Program::getTotalWeight)
        .findFirst()
        .orElse(unbalanced.getTotalWeight());

    return unbalanced.getWeight() - weightDiff;
  }

  public static Day07 create(Path file) throws IOException {
    return new Day07(
        Files.readAllLines(file).stream()
            .map(PROGRAM_PATTERN::matcher)
            .filter(Matcher::matches)
            .map(m -> {
              List<String> children = new ArrayList<>();

              if (m.group(CHILDREN_TAG) != null) {
                Arrays.stream(m.group(CHILDREN_TAG).split(","))
                    .map(String::trim)
                    .forEach(children::add);
              }

              return new Program.ProgramInitializer(
                  m.group(NAME_TAG),
                  Integer.parseInt(m.group(WEIGHT_TAG)),
                  children
              );
            })
            .reduce(
                new Program.Builder(),
                Program.Builder::addProgram,
                (b1, b2) -> (new Program.Builder())
                    .addAll(b1.getPrograms())
                    .addAll(b2.getPrograms())
            )
            .build()
    );
  }

  public static Day07 create(Collection<Program> programs) {
    return new Day07(programs);
  }
}
