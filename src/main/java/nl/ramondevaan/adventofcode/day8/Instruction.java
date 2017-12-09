package nl.ramondevaan.adventofcode.day8;

import lombok.Data;

import java.util.Map;

@Data
public class Instruction {
    private final Assignment  assignment;
    private final Conditional conditional;

    public void execute(Map<Identifier, Register> datastore) {
        if(conditional.check(datastore)) {
            assignment.compute(datastore);
        }
    }
}
