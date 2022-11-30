package nl.ramondevaan.adventofcode.day15;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day15 {
  private final static String ID_TAG = "id";
  private final static String START_TAG = "start";
  private final static String GENERATOR_REGEX =
      "Generator (?<" + ID_TAG + ">\\w+) starts with (?<" + START_TAG + ">\\d+)";
  private final static Pattern GENERATOR_PATTERN = Pattern.compile(GENERATOR_REGEX);

  private final static Map<Identifier, Integer> FACTORS;
  private final static Map<Identifier, Integer> CRITERIA;

  static {
    Map<Identifier, Integer> factorsTemp = new HashMap<>();
    Map<Identifier, Integer> criteriaTemp = new HashMap<>();

    factorsTemp.put(new Identifier("A"), 16807);
    factorsTemp.put(new Identifier("B"), 48271);

    criteriaTemp.put(new Identifier("A"), 4);
    criteriaTemp.put(new Identifier("B"), 8);

    FACTORS = Collections.unmodifiableMap(factorsTemp);
    CRITERIA = Collections.unmodifiableMap(criteriaTemp);
  }

  private final Collection<Generator> generators;

  private Day15(Collection<Generator> generators) {
    this.generators = generators == null ?
        Collections.emptyList() :
        Collections.unmodifiableCollection(generators);
  }

  public long countMatches() {
    return IntStream.range(0, 40000000)
        .mapToObj(i -> generators
            .stream()
            .mapToLong(Generator::next)
            .map(l -> l & 0b1111111111111111)
            .toArray())
        .filter(arr -> Arrays.stream(arr).distinct().count() == 1)
        .count();
  }

  public long countMatchesCriteria() {
    return IntStream.range(0, 5000000)
        .mapToObj(i -> generators
            .stream()
            .mapToLong(Generator::nextByCriterion)
            .map(l -> l & 0b1111111111111111)
            .toArray())
        .filter(arr -> Arrays.stream(arr).distinct().count() == 1)
        .count();
  }

  public static Day15 create(Path file) throws IOException {
    return new Day15(
        Files.readAllLines(file).stream()
            .map(GENERATOR_PATTERN::matcher)
            .filter(Matcher::matches)
            .map(m -> new ImmutablePair<>(
                    new Identifier(m.group(ID_TAG)),
                    Integer.parseInt(m.group(START_TAG))
                )
            )
            .filter(p -> FACTORS.containsKey(p.getLeft()))
            .filter(p -> CRITERIA.containsKey(p.getLeft()))
            .map(p -> new Generator(
                p.getLeft(),
                FACTORS.get(p.getLeft()),
                CRITERIA.get(p.getLeft()),
                p.getRight()
            ))
            .collect(Collectors.toList())
    );
  }

  public static Day15 create(Collection<Generator> generators) {
    return new Day15(new ArrayList<>(generators));
  }
}
