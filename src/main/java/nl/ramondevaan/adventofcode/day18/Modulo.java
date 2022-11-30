package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

@Data
public class Modulo implements Instruction {
  private final Identifier registerId;
  private final Value value;

  @Override
  public void execute(State state, InstructionState instructionState) {
    state.computeRegister(registerId, i -> i %
        (value.hasIdentifier() ?
            state.getRegister(value.getIdentifier()).getValue() :
            value.getValueSafe())
    );
  }

}
