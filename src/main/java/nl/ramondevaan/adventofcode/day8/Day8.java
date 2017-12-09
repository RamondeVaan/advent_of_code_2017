package nl.ramondevaan.adventofcode.day8;

import nl.ramondevaan.adventofcode.day7.Program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Day8 {
    private final static String I_OPERATORS =
            Arrays.stream(BinaryOperatorI.values())
                    .map(BinaryOperatorI::getRepresentation)
                    .collect(Collectors.joining("|"));
    private final static String B_OPERATORS =
            Arrays.stream(BinaryOperatorB.values())
                    .map(BinaryOperatorB::getRepresentation)
                    .collect(Collectors.joining("|"));

    public static Day8 create(Path file) throws IOException {
        return new Day8(
//                Files.lines(file)

        );
    }

    public static Day8 create(Collection<Program> programs) {
        return new Day8(programs);
    }
}
