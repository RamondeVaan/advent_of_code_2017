package nl.ramondevaan.adventofcode.day08;

import java.util.Arrays;

public enum
BinaryOperatorI {
    INCREMENT("inc") {
        @Override
        public int compute(int k, int l) {
            return k + l;
        }
    },
    DECREMENT("dec") {
        @Override
        public int compute(int k, int l) {
            return k - l;
        }
    };

    private final String representation;

    BinaryOperatorI(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public abstract int compute(int k, int l);

    public static BinaryOperatorI parse(String representation) {
        return Arrays.stream(BinaryOperatorI.values())
                .filter(bo -> bo.getRepresentation().equals(representation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Could not parse \"%s\" to a BinaryOperatorI instance",
                                representation
                        )
                ));
    }
}
