package nl.ramondevaan.adventofcode.day3;

public enum Direction {
    UP(0, 1) {
        @Override
        Direction nextDirectionCounterClockWise() {
            return LEFT;
        }
    },
    DOWN(0, -1) {
        @Override
        Direction nextDirectionCounterClockWise() {
            return RIGHT;
        }
    },
    RIGHT(1, 0) {
        @Override
        Direction nextDirectionCounterClockWise() {
            return UP;
        }
    },
    LEFT(-1, 0) {
        @Override
        Direction nextDirectionCounterClockWise() {
            return DOWN;
        }
    };

    private int xDif;
    private int yDif;

    Direction(int xDif, int yDif) {
        this.xDif = xDif;
        this.yDif = yDif;
    }

    public Coordinate move(Coordinate c) {
        return new Coordinate(c.x + xDif, c.y + yDif);
    }

    abstract Direction nextDirectionCounterClockWise();
}
