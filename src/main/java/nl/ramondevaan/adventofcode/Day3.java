package nl.ramondevaan.adventofcode;

import java.util.*;

public class Day3 {

    private enum Direction {
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

    private static class Coordinate {
        final int x;
        final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", x, y);
        }

        public static int manhattanDistance(Coordinate c1, Coordinate c2) {
            return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
        }

        public static List<Coordinate> neighbors(Coordinate c) {
            return Arrays.asList(
                    new Coordinate(c.x - 1, c.y - 1),
                    new Coordinate(c.x - 1, c.y),
                    new Coordinate(c.x - 1, c.y + 1),
                    new Coordinate(c.x, c.y - 1),
                    new Coordinate(c.x, c.y + 1),
                    new Coordinate(c.x + 1, c.y - 1),
                    new Coordinate(c.x + 1, c.y),
                    new Coordinate(c.x + 1, c.y + 1)
            );
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Spiral {
        private int stride;
        private int curStep;
        private Direction direction;
        private boolean incStride;
        private Coordinate nextCoord;

        private Spiral() {
            stride = 1;
            curStep = 0;
            direction = Direction.RIGHT;
            incStride = false;
            nextCoord = new Coordinate(0, 0);
        }

        public Coordinate addCoord() {
            if(curStep == stride) {
                if(incStride) {
                    stride++;
                }

                incStride = !incStride;
                curStep = 0;
                direction = direction.nextDirectionCounterClockWise();
            }

            curStep++;
            Coordinate temp = nextCoord;
            nextCoord = direction.move(nextCoord);
            return temp;
        }
    }

    private static class SaveSpiral extends Spiral {
        public final Map<Coordinate, Integer> coords;

        private SaveSpiral() {
            this.coords = new HashMap<>();
        }

        @Override
        public Coordinate addCoord() {
            return addCoord(0);
        }

        public Coordinate addCoord(int value) {
            Coordinate c = super.addCoord();
            coords.put(c, value);
            return c;
        }
    }

    public static int spiralDist(int n) {
        Spiral s = new Spiral();
        final Coordinate start = new Coordinate(0, 0);

        Coordinate c = s.addCoord();

        for(int i = 0; i < n - 1; i++) {
            c = s.addCoord();
        }

        return Coordinate.manhattanDistance(start, c);
    }

    public static int stressTest(int n) {
        SaveSpiral s = new SaveSpiral();

        int curVal = 1;
        Coordinate c = s.addCoord(curVal);

        while(curVal < n) {
            c = s.addCoord();

            curVal = 0;

            for(Coordinate p : Coordinate.neighbors(c)) {
                Integer val = s.coords.get(p);

                if(val != null) {
                    curVal += val;
                }
            }

            s.coords.put(c, curVal);
        }

        return curVal;
    }

    public static void main(String[] args) {
        System.out.printf("Exercise 1: %d%n", spiralDist(265149));
        System.out.printf("Exercise 2: %d%n", stressTest(265149));
    }
}
