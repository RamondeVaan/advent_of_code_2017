package nl.ramondevaan.adventofcode.day18;

import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Data
public class InstructionState implements Iterator<Instruction> {
  private final List<Instruction> instructions;
  private int currentInstruction;

  @Override
  public boolean hasNext() {
    return currentInstruction >= 0 &&
        currentInstruction < instructions.size();
  }

  @Override
  public Instruction next() {
    return instructions.get(currentInstruction++);
  }
}
