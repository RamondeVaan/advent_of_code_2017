package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

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

}
