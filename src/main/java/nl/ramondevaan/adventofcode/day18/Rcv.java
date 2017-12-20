package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Identifier> getRegisterIds() {
        List<Identifier> ret = new ArrayList<>();

        if(x.hasIdentifier()) {
            ret.add(x.getIdentifier());
        }

        return ret;
    }
}
