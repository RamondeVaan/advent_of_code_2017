package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Jump implements Instruction {
    private final Value x;
    private final Value y;

    @Override
    public void execute(State state, InstructionState instructionState) {
        long xVal = x.hasIdentifier() ?
                state.getRegister(x.getIdentifier()).getValue() :
                x.getValueSafe();

        if (xVal > 0) {
            int yVal = (int) (y.hasIdentifier() ?
                    state.getRegister(y.getIdentifier()).getValue() :
                    y.getValueSafe());
            yVal--;

            instructionState.setCurrentInstruction(
                    instructionState.getCurrentInstruction() + yVal
            );
        }
    }

    @Override
    public List<Identifier> getRegisterIds() {
        List<Identifier> ret = new ArrayList<>();

        if (x.hasIdentifier()) {
            ret.add(x.getIdentifier());
        }
        if (y.hasIdentifier()) {
            ret.add(y.getIdentifier());
        }

        return ret;
    }
}
