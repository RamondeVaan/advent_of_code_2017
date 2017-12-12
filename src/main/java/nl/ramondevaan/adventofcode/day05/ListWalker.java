package nl.ramondevaan.adventofcode.day05;

import java.util.List;

public class ListWalker {
    private Incrementer incrementer;

    public ListWalker(Incrementer incrementer) {
        this.incrementer = incrementer;
    }

    public int walk(List<Integer> values) {
        int cur   = 0;
        int next;
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
