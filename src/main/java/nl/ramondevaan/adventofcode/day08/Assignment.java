package nl.ramondevaan.adventofcode.day08;

import lombok.Data;

import java.util.Map;

@Data
public class Assignment {
    private final Identifier      registerId;
    private final BinaryOperatorI operator;
    private final int             value;

    public void compute(Map<Identifier, Register> datastore) {
        datastore.put(
                registerId,
                new Register(
                        registerId,
                        operator.compute(
                                datastore.get(registerId).getValue(),
                                value
                        )
                )
        );
    }
}
