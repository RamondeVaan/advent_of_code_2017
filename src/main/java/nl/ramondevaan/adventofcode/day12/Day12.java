package nl.ramondevaan.adventofcode.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day12 {
    private final static String  PROGRAM_ID_TAG  = "programid";
    private final static String  PEERS_ID_TAG    = "peers";
    private final static String  PROGRAM_REGEX   =
            "(?<" + PROGRAM_ID_TAG + ">\\d+) <-> (?<" + PEERS_ID_TAG + ">\\d+(, \\d+)*)";
    private final static Pattern PROGRAM_PATTERN = Pattern.compile(PROGRAM_REGEX);
    private final Collection<Program> input;

    private Day12(Collection<Program> input) {
        this.input = input == null ?
                Collections.emptyList() :
                Collections.unmodifiableCollection(input);
    }

    private static void walk(final Set<Identifier> visited, Program p) {
        visited.add(p.getId());
        p.getPeers().stream()
         .filter(q -> !visited.contains(q.getId()))
         .forEach(q -> walk(visited, q));
    }

    public static Day12 create(Path file) throws IOException {
        return new Day12(
                new Program.Builder()
                        .addAll(
                                Files.lines(file)
                                     .map(PROGRAM_PATTERN::matcher)
                                     .filter(Matcher::matches)
                                     .map(m -> {
                                         Identifier id = new Identifier(Integer.parseInt(m.group(PROGRAM_ID_TAG)));

                                         List<Identifier> peers =
                                                 Arrays.stream(m.group(PEERS_ID_TAG).split(","))
                                                       .map(String::trim)
                                                       .map(Integer::parseInt)
                                                       .map(Identifier::new)
                                                       .collect(Collectors.toList());

                                         return new Program.ProgramInitializer(id, peers);
                                     })
                                     .collect(Collectors.toList())
                        )
                        .build()
        );
    }

    public static Day12 create(List<Program> input) {
        return new Day12(input);
    }

    public int numInGroup0() {
        final Identifier id0 = new Identifier(0);
        Program p0 = input.stream()
                          .filter(p -> p.getId().equals(id0))
                          .findFirst()
                          .orElseThrow(() -> new IllegalArgumentException("There was no program with id 0"));

        Set<Identifier> visited = new HashSet<>();
        walk(visited, p0);

        return visited.size();
    }

    public int numberOfGroups() {
        Set<Program> allPrograms = new HashSet<>(input);

        int count = 0;

        while(!allPrograms.isEmpty()) {
            Optional<Program> po = allPrograms.stream().findAny();
            if(!po.isPresent()) {
                break;
            }
            Program p = po.get();

            Set<Identifier> visited = new HashSet<>();
            walk(visited, p);

            allPrograms.removeIf(q -> visited.contains(q.getId()));
            count++;
        }

        return count;
    }
}
