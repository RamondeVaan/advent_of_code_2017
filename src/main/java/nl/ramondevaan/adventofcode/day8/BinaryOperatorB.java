package nl.ramondevaan.adventofcode.day8;

import java.util.Arrays;

public enum BinaryOperatorB {
    LESSER("<") {
        @Override
        public boolean check(int k, int l) {
            return k < l;
        }
    },
    GREATER(">") {
        @Override
        public boolean check(int k, int l) {
            return k > l;
        }
    },
    LESSER_OR_EQUAL("<=") {
        @Override
        public boolean check(int k, int l) {
            return k <= l;
        }
    },
    GREATER_OR_EQUAL(">=") {
        @Override
        public boolean check(int k, int l) {
            return k >= l;
        }
    },
    EQUAL("==") {
        @Override
        public boolean check(int k, int l) {
            return k == l;
        }
    },
    NOT_EQUAL("!=") {
        @Override
        public boolean check(int k, int l) {
            return k != l;
        }
    };

    private final String representation;

    BinaryOperatorB(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public abstract boolean check(int k, int l);

    public static BinaryOperatorB parse(String representation) {
        return Arrays.stream(BinaryOperatorB.values())
                .filter(bo -> bo.getRepresentation().equals(representation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(
                                "Could not parse \"%s\" to a BinaryOperatorB instance",
                                representation
                        )
                ));
    }
}
