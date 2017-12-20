package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

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

}
