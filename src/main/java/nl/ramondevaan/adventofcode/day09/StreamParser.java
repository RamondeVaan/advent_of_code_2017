package nl.ramondevaan.adventofcode.day09;

import java.util.ArrayList;
import java.util.List;

public class StreamParser {
    private String input;
    private int    position;
    private int    character;

    public StreamParser() {
        reset();
    }

    public void setInput(String input) {
        this.input = input;
    }

    private void reset() {
        this.position = -1;
        this.character = -1;
    }

    public Stream parse() {
        reset();

        if (hasNextChar()) {
            readNextChar();
            switch (character) {
                case '{':
                    return parseGroup();
                case '<':
                    return parseGarbage();
                default:
                    throw new IllegalArgumentException(
                            String.format(
                                    "Could not parse character \"%s\" at position %d.",
                                    character,
                                    position
                            )
                    );
            }
        }

        throw new IllegalArgumentException(
                "Input was empty"
        );
    }

    private Group parseGroup() {
        List<Stream> children = new ArrayList<>();

        int start = position;

        while (hasNextChar()) {
            readNextChar();
            switch (character) {
                case '{':
                    children.add(parseGroup());
                    break;
                case '}':
                    Group g = new Group();
                    g.setChildren(children);
                    children.forEach(p -> p.setParent(g));
                    return g;
                case '<':
                    children.add(parseGarbage());
                    break;
                case ',':
                    //Ignore
                    break;
                default:
                    throw new IllegalArgumentException(
                            String.format(
                                    "Did not expect \"%s\" at index %d.",
                                    character,
                                    position
                            )
                    );
            }
        }

        throw new IllegalArgumentException(
                String.format(
                        "Stream ended before group starting at index %d was closed.",
                        start
                )
        );
    }

    private Garbage parseGarbage() {
        StringBuilder characters = new StringBuilder();

        int start = position;

        while (hasNextChar()) {
            readNextChar();
            switch (character) {
                case '>':
                    return new Garbage(characters.toString());
                case '!':
                    if (!hasNextChar()) {
                        throw new IllegalArgumentException(
                                String.format(
                                        "Stream ended abruptly parsing ! character at %d.",
                                        position
                                )
                        );
                    }
                    readNextChar();
                    break;
                default:
                    characters.append((char) character);
                    break;
            }
        }

        throw new IllegalArgumentException(
                String.format(
                        "Stream ended before garbage starting at index %d was closed.",
                        start
                )
        );
    }

    private void readNextChar() {
        position++;
        character = input.charAt(position);
    }

    private boolean hasNextChar() {
        return input != null &&
                (position + 1) < input.length();
    }
}
