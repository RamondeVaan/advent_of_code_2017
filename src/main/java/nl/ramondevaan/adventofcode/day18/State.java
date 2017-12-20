package nl.ramondevaan.adventofcode.day18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public abstract class State {
    private List<Register> registers;

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(Collection<Register> registers) {
        this.registers = Collections.unmodifiableList(new ArrayList<>(registers));
    }

    public Register getRegister(Identifier registerId) {
        return registers
                .stream()
                .filter(r -> r.getId().equals(registerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Could not find register with id \"%s\".",
                        registerId.getId()
                )));
    }

    public void setRegister(Register register) {
        int index = IntStream
                .range(0, this.registers.size())
                .filter(i -> this.registers.get(i).getId().equals(register.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Could not find register with id \"%s\".",
                        register.getId().getId()
                )));

        List<Register> temp = new ArrayList<>(registers);
        temp.set(index, register);
        this.registers = Collections.unmodifiableList(temp);
    }

    public void computeRegister(Identifier registerId, RegisterFunction function) {
        int index = IntStream
                .range(0, this.registers.size())
                .filter(i -> this.registers.get(i).getId().equals(registerId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Could not find register with id \"%s\".",
                        registerId.getId()
                )));

        List<Register> registers = new ArrayList<>(this.registers);
        registers.set(index, new Register(
                registerId,
                function.compute(
                        registers.get(index).getValue()
                )
        ));
        this.registers = Collections.unmodifiableList(registers);
    }

    public abstract void snd(long val);

    public abstract boolean rcv(Value value);
}
