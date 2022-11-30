package nl.ramondevaan.adventofcode.day23;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Pattern;

public class InstructionParser {

  private final static Pattern PATTERN = Pattern.compile("^(\\w{3}) (-?\\w+) (-?\\w+)$");

  public Instruction parse(final String line) {
    final var matcher = PATTERN.matcher(line);

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    final InstructionType type = switch (matcher.group(1)) {
      case "set" -> InstructionType.SET;
      case "sub" -> InstructionType.SUB;
      case "mul" -> InstructionType.MUL;
      case "jnz" -> InstructionType.JNZ;
      default -> throw new IllegalArgumentException();
    };
    final var x = parseValue(matcher.group(2));
    final var y = parseValue(matcher.group(3));

    return new Instruction(type, x, y);
  }

  private Value parseValue(String s) {
    if (NumberUtils.isCreatable(s)) {
      return new Constant(Integer.parseInt(s));
    } else {
      return new Reference(s.charAt(0) - 'a');
    }
  }
}
