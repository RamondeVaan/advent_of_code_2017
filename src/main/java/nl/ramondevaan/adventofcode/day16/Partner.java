package nl.ramondevaan.adventofcode.day16;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class Partner implements DanceMove {
    private final Identifier program1;
    private final Identifier program2;

    @Override
    public List<Program> execute(List<Program> programs) {
        final int position1 = IntStream
                .range(0, programs.size())
                .filter(i -> programs.get(i).getId().equals(program1))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Could not find program with id \"%s\" in list %s",
                        program1,
                        programs.toString()
                )));
        final int position2 = IntStream
                .range(0, programs.size())
                .filter(i -> programs.get(i).getId().equals(program2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Could not find program with id \"%s\" in list %s",
                        program2,
                        programs.toString()
                )));

        ArrayList<Program> ret = new ArrayList<>(programs);
        Collections.swap(ret, position1, position2);

        return ret;
    }
}
