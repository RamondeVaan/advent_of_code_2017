package nl.ramondevaan.adventofcode.day18;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class State {
    private final Map<Identifier, Register> registers;

    public State() {
        this(Collections.emptyList());
    }

    public State(Collection<Register> registers) {
        this.registers = new HashMap<>();
        registers.forEach(r -> this.registers.put(r.getId(), r));
    }

    public Collection<Register> getRegisters() {
        return Collections.unmodifiableCollection(registers.values());
    }

    public Register getRegister(Identifier registerId) {
        if(!registers.containsKey(registerId)) {
            Register r = new Register(registerId, 0);
            registers.put(registerId, r);
            return r;
        }
        return registers.get(registerId);
    }

    public void setRegister(Register register) {
        registers.put(register.getId(), register);
    }

    public void computeRegister(Identifier registerId, RegisterFunction function) {
        if(!registers.containsKey(registerId)) {
            registers.put(
                    registerId,
                    new Register(
                            registerId,
                            function.compute(0)
                    )
            );
            return;
        }

        registers.put(
                registerId,
                new Register(
                        registerId,
                        function.compute(registers.get(registerId).getValue())
                )
        );
    }

    public abstract void snd(long val);

    public abstract boolean rcv(Value value);
}
