package nl.ramondevaan.adventofcode.day23;

import java.util.List;

@SuppressWarnings("unused")
public class Day23 {

  private final static int A = 0;
  private final static int B = 1;
  private final static int C = 2;
  private final static int D = 3;
  private final static int E = 4;
  private final static int F = 5;
  private final static int G = 6;
  private final static int H = 7;
  private final List<Instruction> instructions;

  public Day23(final List<String> lines) {
    final var parser = new InstructionParser();

    this.instructions = lines.stream().map(parser::parse).toList();
  }

  public int exercise1() {
    var index = 0;
    Instruction instruction;
    final var registers = new int[8];
    var multCount = 0;

    while (index < instructions.size()) {
      instruction = instructions.get(index);

      switch (instruction.type()) {
        case SET -> {
          registers[instruction.x().getValue()] = getValue(registers, instruction.y());
          index++;
        }
        case SUB -> {
          registers[instruction.x().getValue()] -= getValue(registers, instruction.y());
          index++;
        }
        case MUL -> {
          registers[instruction.x().getValue()] *= getValue(registers, instruction.y());
          multCount++;
          index++;
        }
        case JNZ -> {
          if (getValue(registers, instruction.x()) != 0) {
            index += instruction.y().getValue();
          } else {
            index++;
          }
        }
      }
    }

    return multCount;
  }

  public int exercise2() {
    final var registers = new int[8];
    registers[A] = 1;
    registers[B] = 65 * 100 + 100000;
    registers[C] = registers[1] + 17000;

    do {
      registers[F] = 1;
      registers[D] = 2;
      for (int d = registers[D]; (d * d) < registers[B]; d++) {
        if (registers[B] % d == 0) {
          registers[F] = 0;
          break;
        }
      }
      if (registers[F] == 0) {
        registers[H]++;
      }
      registers[G] = registers[B] - registers[C];
      registers[B] += 17;
    } while (registers[G] != 0);

    return registers[H];
  }

  private int getValue(int[] registers, Value value) {
    if (value instanceof Constant constant) {
      return constant.getValue();
    } else if (value instanceof Reference reference) {
      return registers[reference.index()];
    } else {
      throw new IllegalArgumentException();
    }
  }
}
