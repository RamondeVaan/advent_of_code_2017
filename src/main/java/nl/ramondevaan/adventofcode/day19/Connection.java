package nl.ramondevaan.adventofcode.day19;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Data
@ToString(exclude = {"in", "out"})
public class Connection {
    @Setter(value = AccessLevel.PRIVATE)
    private      Connection in;
    @Setter(value = AccessLevel.PRIVATE)
    private      Connection out;
    public final int        representation;
    public final Coordinate coordinate;

    public Stream<Connection> walk() {
        Stream.Builder<Connection> builder = Stream.builder();
        builder.accept(this);

        Connection c = this.out;

        while (c != null) {
            builder.accept(c);
            c = c.out;
        }

        return builder.build();
    }

    public static Collection<Connection> parse(final int[][] input) {
        final int height = input.length;
        final int width = IntStream
                .range(0, height)
                .filter(i -> input[i] != null)
                .map(i -> input[i].length)
                .max()
                .orElse(0);

        int[][] chars = new int[height][width];
        for(int r = 0; r < input.length; r++) {
            Arrays.fill(chars[r], ' ');
            if(input[r] != null) {
                System.arraycopy(input[r], 0, chars[r], 0, input[r].length);
            }
        }

        final ConnectionType[][] types = IntStream
                .range(0, height)
                .boxed()
                .map(i -> Arrays
                        .stream(chars[i])
                        .mapToObj(ConnectionType::parse)
                        .toArray(ConnectionType[]::new))
                .toArray(ConnectionType[][]::new);

        final Connection[][] connections = IntStream
                .range(0, height)
                .boxed()
                .map(i -> IntStream
                        .range(0, width)
                        .mapToObj(j -> new Connection(
                                chars[i][j],
                                new Coordinate(i, j)
                        ))
                        .toArray(Connection[]::new))
                .toArray(Connection[][]::new);

        Coordinate coordinate = IntStream
                .range(0, height)
                .filter(c -> types[0][c] == ConnectionType.NORTH_SOUTH)
                .mapToObj(c -> new Coordinate(0, c))
                .reduce((c1, c2) -> {
                    throw new IllegalArgumentException(
                            "Multiple possible entry-points found"
                    );
                })
                .orElseThrow(() -> new IllegalArgumentException(
                        "No entry-points found"
                ));

        Set<Direction> directions = Collections.singleton(Direction.DOWN);
        ConnectionType tempCon;

        outer:
        while (true) {
            //Try to go one step in every direction
            dir:
            for (Direction d : directions) {
                for (Coordinate tempCoord : d
                        .walk(coordinate, 0, width, 0, height)
                        .collect(Collectors.toList())) {
                    tempCon = types[tempCoord.r][tempCoord.c];
                    if (tempCon == ConnectionType.EMPTY) {
                        continue dir;
                    }
                    if (!tempCon.directions.contains(d.opposite())) {
                        continue;
                    }
                    connections[coordinate.r][coordinate.c].setOut(connections[tempCoord.r][tempCoord.c]);
                    connections[tempCoord.r][tempCoord.c].setIn(connections[coordinate.r][coordinate.c]);
                    coordinate = tempCoord;
                    directions = tempCon.getOutDirections(d);
                    continue outer;
                }
            }
            break;
        }

        return Arrays
                .stream(connections, 0, height)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }
}
