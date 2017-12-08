package nl.ramondevaan.adventofcode.day7;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
    private final static String  CHILDREN_TAG    = "children";
    private final static String  WEIGHT_TAG      = "weight";
    private final static String  NAME_TAG        = "name";
    private final static String  PROGRAM_REGEX   =
            "^(?<" + NAME_TAG + ">.+) \\((?<" + WEIGHT_TAG + ">\\d+)\\)( ->(?<" + CHILDREN_TAG + ">.+))?$";
    private final static Pattern PROGRAM_PATTERN = Pattern.compile(PROGRAM_REGEX);

    private final Collection<Program> programs;

    private Day7(Collection<Program> programs) {
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

    public String unbalanced() {
        Program unbalanced = programs.stream()
                .filter(p -> p.getChildren().size() > 0)
                .map(p -> new ImmutablePair<>(p, p.getChildren()))
                .map(p -> new ImmutablePair<>(
                        p.getLeft(),
                        p.getRight().stream().mapToInt(Program::getTotalWeight).distinct().count()
                ))
                .filter(p -> p.getRight() > 1)
                .map(ImmutablePair::getLeft)
                .findFirst()
                .orElse(null);

        programs.stream()
                .filter(p -> p.getChildren().size() > 1)
                .map(p -> {
                    p.getChildren().stream()
                            .
                })

        if(unbalanced == null) {
            return null;
        }


    }

//    public static void main(String[] args) {
//        System.out.println("");
//        if (args.length != 1) {
//            System.err.println("Program requires input folder as argument");
//            return;
//        }
//
//        Path input = Paths.get(args[0], "day7.txt");
//        try {
////            Files.lines(input)
////                    .map(s -> Arrays.stream(s.split("\\s+"))
////                            .map(Integer::parseInt)
////                            .collect(Collectors.toList()))
////                    .findFirst()
////                    .orElse(new ArrayList<>())
//
////            System.out.printf("Exercise 1: %d%n", exercise1(values));
////            System.out.printf("Exercise 2: %d%n", exercise2(values));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static Day7 create(Path file) throws IOException {
        return new Day7(
                Files.lines(file)
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

    public static Day7 create(Collection<Program> programs) {
        return new Day7(programs);
    }
}
