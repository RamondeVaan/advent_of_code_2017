package nl.ramondevaan.adventofcode.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day08 {
    private final static String I_OPERATORS =
            Arrays.stream(BinaryOperatorI.values())
                  .map(BinaryOperatorI::getRepresentation)
                  .map(Pattern::quote)
                  .collect(Collectors.joining("|"));
    private final static String B_OPERATORS =
            Arrays.stream(BinaryOperatorB.values())
                  .map(BinaryOperatorB::getRepresentation)
                  .map(Pattern::quote)
                  .collect(Collectors.joining("|"));

    private final static String ASSIGNMENT_TAG  = "assignment";
    private final static String CONDITIONAL_TAG = "conditional";
    private final static String OPERATOR_I_TAG  = "operatori";
    private final static String OPERATOR_B_TAG  = "operatorb";
    private final static String REGISTER_A_TAG  = "registera";
    private final static String REGISTER_C_TAG  = "registerc";
    private final static String VALUE_A_TAG     = "valuea";
    private final static String VALUE_C_TAG     = "valuec";

    private final static String  ASSIGNMENT_REGEX    =
            "(?<" + ASSIGNMENT_TAG + ">(?<" + REGISTER_A_TAG + ">\\w+) (?<" + OPERATOR_I_TAG + ">" +
                    I_OPERATORS + ") (?<" + VALUE_A_TAG + ">-?\\d+))";
    private final static String  CONDITIONAL_REGEX   =
            "(?<" + CONDITIONAL_TAG + ">if (?<" + REGISTER_C_TAG + ">\\w+) (?<" + OPERATOR_B_TAG + ">" +
                    B_OPERATORS + ") (?<" + VALUE_C_TAG + ">-?\\d+))";
    private final static String  INSTRUCTION_REGEX   = ASSIGNMENT_REGEX + " " + CONDITIONAL_REGEX;
    private final static Pattern INSTRUCTION_PATTERN = Pattern.compile(INSTRUCTION_REGEX);

    private final List<Instruction> instructions;

    private Day08(List<Instruction> instructions) {
        this.instructions = instructions == null ?
                            Collections.emptyList() :
                            Collections.unmodifiableList(instructions);
    }

    private Map<Identifier, Register> initializeDatastore() {
        Map<Identifier, Register> datastore = new HashMap<>();

        instructions.stream()
                    .flatMap(i -> Stream.of(
                            i.getAssignment().getRegisterId(),
                            i.getConditional().getRegisterId()
                    ))
                    .distinct()
                    .forEach(i -> datastore.put(i, new Register(i, 0)));

        return datastore;
    }

    public int maximumValueAtEnd() {
        final Map<Identifier, Register> datastore = initializeDatastore();

        instructions.forEach(i -> i.execute(datastore));

        return datastore.values().stream()
                        .mapToInt(Register::getValue)
                        .max()
                        .orElse(-1);
    }

    public int maximumValueDuringExecution() {
        final Map<Identifier, Register> datastore = initializeDatastore();

        return instructions
                .stream()
                .peek(i -> i.execute(datastore))
                .mapToInt(i -> datastore.values().stream()
                                        .mapToInt(r -> r.getValue())
                                        .max().orElse(-1))
                .max()
                .orElse(-1);
    }

    public static Day08 create(Path file) throws IOException {
        return new Day08(
                Files.lines(file)
                     .map(INSTRUCTION_PATTERN::matcher)
                     .filter(Matcher::matches)
                     .map(m -> new Instruction(
                             new Assignment(
                                     new Identifier(m.group(REGISTER_A_TAG)),
                                     BinaryOperatorI.parse(m.group(OPERATOR_I_TAG)),
                                     Integer.parseInt(m.group(VALUE_A_TAG))
                             ),
                             new Conditional(
                                     new Identifier(m.group(REGISTER_C_TAG)),
                                     BinaryOperatorB.parse(m.group(OPERATOR_B_TAG)),
                                     Integer.parseInt(m.group(VALUE_C_TAG))
                             )
                     ))
                     .collect(Collectors.toList())
        );
    }

    public static Day08 create(List<Instruction> instructions) {
        return new Day08(new ArrayList<>(instructions));
    }
}
