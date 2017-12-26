package nl.ramondevaan.adventofcode.day19;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum Direction {
    UP(0, 1) {
        @Override
        public Direction opposite() {
            return DOWN;
        }

        @Override
        public Direction clockwise() {
            return RIGHT;
        }

        @Override
        public Direction counterClockwise() {
            return LEFT;
        }

        @Override
        public Stream<Coordinate> walk(
                Coordinate from, int minWidth, int maxWidth, int minHeight, int maxHeight
        ) {
            return IntStream
                    .range(minHeight, from.r)
                    .map(i -> from.r - i + minHeight - 1)
                    .mapToObj(r -> new Coordinate(r, from.c));
        }
    },
    DOWN(0, -1) {
        @Override
        public Direction opposite() {
            return UP;
        }

        @Override
        public Direction clockwise() {
            return LEFT;
        }

        @Override
        public Direction counterClockwise() {
            return RIGHT;
        }

        @Override
        public Stream<Coordinate> walk(
                Coordinate from, int minWidth, int maxWidth, int minHeight, int maxHeight
        ) {
            return IntStream
                    .range(from.r + 1, maxHeight)
                    .mapToObj(r -> new Coordinate(r, from.c));
        }
    },
    RIGHT(1, 0) {
        @Override
        public Direction opposite() {
            return LEFT;
        }

        @Override
        public Direction clockwise() {
            return DOWN;
        }

        @Override
        public Direction counterClockwise() {
            return UP;
        }

        @Override
        public Stream<Coordinate> walk(
                Coordinate from, int minWidth, int maxWidth, int minHeight, int maxHeight
        ) {
            return IntStream
                    .range(from.c + 1, maxWidth)
                    .mapToObj(c -> new Coordinate(from.r, c));
        }
    },
    LEFT(-1, 0) {
        @Override
        public Direction opposite() {
            return RIGHT;
        }

        @Override
        public Direction clockwise() {
            return UP;
        }

        @Override
        public Direction counterClockwise() {
            return DOWN;
        }

        @Override
        public Stream<Coordinate> walk(
                Coordinate from, int minWidth, int maxWidth, int minHeight, int maxHeight
        ) {
            return IntStream
                    .range(minWidth, from.c)
                    .map(i -> from.c - i + minWidth - 1)
                    .mapToObj(c -> new Coordinate(from.r, c));
        }
    };

    public final int xDif;
    public final int yDif;

    Direction(final int xDif, final int yDif) {
        this.xDif = xDif;
        this.yDif = yDif;
    }

    public Coordinate move(Coordinate c) {
        return new Coordinate(c.r + xDif, c.c + yDif);
    }

    public abstract Direction opposite();

    public abstract Direction clockwise();

    public abstract Direction counterClockwise();

    public abstract Stream<Coordinate> walk(
            final Coordinate from,
            final int minWidth,
            final int maxWidth,
            final int minHeight,
            final int maxHeight
    );
}
