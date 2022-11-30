package nl.ramondevaan.adventofcode.day21;

import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class RuleParser {

  private final static Pattern PATTERN = Pattern.compile("^(?<pattern>[.#/]+) => (?<result>[.#/]+)$");
  private final GridParser gridParser;

  public Rule parse(final String toParse) {
    final var matcher = PATTERN.matcher(toParse);

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    return new Rule(
        gridParser.parse(matcher.group("pattern")),
        gridParser.parse(matcher.group("result"))
    );
  }
}
