package nl.ramondevaan.adventofcode.day13;

import lombok.Data;

@Data
public class Layer {
  private final LayerIdentifier id;
  private final int depth;
  private final int range;
}
