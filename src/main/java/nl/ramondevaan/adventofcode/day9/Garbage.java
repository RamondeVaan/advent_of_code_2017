package nl.ramondevaan.adventofcode.day9;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Garbage extends Stream {
    private final String characters;
}
