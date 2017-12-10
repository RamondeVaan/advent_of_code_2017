package nl.ramondevaan.adventofcode.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Day9 {
    private final String input;

    private Day9(String input) {
        this.input = input;
    }

    private Stream parse() {
        StreamParser parser = new StreamParser();
        parser.setInput(input);

        return parser.parse();
    }

    public int groupScore() {
        Stream s = parse();

        if(s instanceof Group) {
            return getScore((Group) s, 1);
        }

        return 0;
    }

    private int getScore(Group s, int depth) {
        return depth +
                s.getChildren().stream()
                 .filter(t -> t instanceof Group)
                 .map(t -> (Group) t)
                 .mapToInt(t -> getScore(t, depth + 1))
                 .sum();
    }

    public int numberOfCharacters() {
        return numberOfCharacters(parse());
    }

    private int numberOfCharacters(Stream s) {
        if(s instanceof Group) {
            return ((Group) s).getChildren().stream()
                       .mapToInt(this::numberOfCharacters)
                       .sum();
        } else if (s instanceof Garbage) {
            return ((Garbage) s).getCharacters().length();
        }

        return 0;
    }

    public static Day9 create(Path file) throws IOException {
        return new Day9(Files.lines(file).collect(Collectors.joining()));
    }

    public static Day9 create(String input) {
        return new Day9(input);
    }
}
