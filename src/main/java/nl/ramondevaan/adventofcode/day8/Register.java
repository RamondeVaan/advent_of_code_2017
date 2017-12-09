package nl.ramondevaan.adventofcode.day8;

import lombok.Data;

import java.util.Objects;

@Data
public class Register {
    private final Identifier id;
    private final int        value;
}
