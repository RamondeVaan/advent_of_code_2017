package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Snd implements Instruction {
    private final Value x;

    @Override
    public void execute(State state, InstructionState instructionState) {
        state.snd(
                x.hasIdentifier() ?
                        state.getRegister(x.getIdentifier()).getValue() :
                        x.getValueSafe()
        );
    }

    @Override
    public List<Identifier> getRegisterIds() {
        List<Identifier> ret = new ArrayList<>();

        if (x.hasIdentifier()) {
            ret.add(x.getIdentifier());
        }

        return ret;
    }
}
