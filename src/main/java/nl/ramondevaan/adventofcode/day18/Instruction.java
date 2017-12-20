package nl.ramondevaan.adventofcode.day18;

public interface Instruction {
    void execute(State state, InstructionState instructionState);
}
