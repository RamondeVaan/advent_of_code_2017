package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Add implements Instruction {
    private final Identifier registerId;
    private final Value      value;

    @Override
    public void execute(State state, InstructionState instructionState) {
        state.computeRegister(registerId, i -> i +
                (value.hasIdentifier() ?
                state.getRegister(value.getIdentifier()).getValue() :
                value.getValueSafe())
        );
    }

    @Override
    public List<Identifier> getRegisterIds() {
        List<Identifier> ret = new ArrayList<>();
        ret.add(registerId);

        if(value.hasIdentifier()) {
            ret.add(value.getIdentifier());
        }

        return ret;
    }
}
