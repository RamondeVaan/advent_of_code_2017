package nl.ramondevaan.adventofcode.day18;

import java.util.List;

public interface Instruction {
    void execute(State state, InstructionState instructionState);
    List<Identifier> getRegisterIds();
}
