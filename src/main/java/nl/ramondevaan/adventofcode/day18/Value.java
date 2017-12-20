package nl.ramondevaan.adventofcode.day18;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Value {
    @Getter
    private final Identifier identifier;
    private final Long    value;

    public Value(Identifier identifier) {
        this.identifier = identifier;
        this.value = null;
    }

    public Value(Long value) {
        this.value = value;
        this.identifier = null;
    }

    public long getValueSafe() {
        return (value == null ? 0 : value);
    }

    public boolean hasIdentifier() {
        return identifier != null;
    }

    public static Value parse(String s) {
        try {
            return new Value(Long.parseLong(s));
        } catch (Exception e) {
            return new Value(new Identifier(s));
        }
    }
}
