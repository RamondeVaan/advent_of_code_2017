package nl.ramondevaan.adventofcode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {
    private interface Incrementer {
        int increment(int index, int value);
    }

    private static class ListWalker {
        private Incrementer   incrementer;

        public ListWalker(Incrementer incrementer) {
            this.incrementer = incrementer;
        }

        public int walk(List<Integer> values) {
            int cur   = 0;
            int next  = 0;
            int count = 0;

            while (cur >= 0 && cur < values.size()) {
                next = cur + values.get(cur);
                values.set(cur, incrementer.increment(cur, values.get(cur)));
                cur = next;
                count++;
            }

            return count;
        }
    }

    private static int numberOfSteps(List<Integer> values) {
        List<Integer> copy = new ArrayList<>(values);
        ListWalker walker = new ListWalker((index, value) -> value + 1);
        return walker.walk(copy);
    }

    private static int numberOfSteps2(List<Integer> values) {
        List<Integer> copy = new ArrayList<>(values);
        ListWalker walker = new ListWalker((index, value) -> value + (value >= 3 ? -1 : 1));
        return walker.walk(copy);
    }

    public static void main(String[] args) {
        Path input = Paths.get("D:\\Projects\\adventofcode\\input\\Day5.txt");
        try {
            List<Integer> values = Files.lines(input)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            System.out.printf("Exercise 1: %d%n", numberOfSteps(values));
            System.out.printf("Exercise 2: %d%n", numberOfSteps2(values));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
