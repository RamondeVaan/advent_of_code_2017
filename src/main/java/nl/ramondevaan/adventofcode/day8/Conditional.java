package nl.ramondevaan.adventofcode.day8;

import lombok.Data;

import java.util.Map;

@Data
public class Conditional {
    private final Identifier      registerId;
    private final BinaryOperatorB operator;
    private final int             value;

    public boolean check(Map<Identifier, Register> datastore) {
        return operator.check(
                datastore.get(registerId).getValue(),
                value
        );
    }
}
