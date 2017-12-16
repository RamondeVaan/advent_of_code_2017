package nl.ramondevaan.adventofcode.day16;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Spin implements DanceMove {
    private final int size;

    @Override
    public List<Program> execute(List<Program> programs) {
        final int from = programs.size() - size;

        ArrayList<Program> ret = new ArrayList<>();
        ret.addAll(programs.subList(from, programs.size()));
        ret.addAll(programs.subList(0, from));

        return ret;
    }
}
