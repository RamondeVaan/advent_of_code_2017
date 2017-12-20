package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

@Data
public class Rcv implements Instruction {
    private final Value x;

    @Override
    public void execute(State state, InstructionState instructionState) {
        if(!state.rcv(x)) {
            instructionState.setCurrentInstruction(
                    instructionState.getCurrentInstruction() - 1
            );
        }
    }

}
