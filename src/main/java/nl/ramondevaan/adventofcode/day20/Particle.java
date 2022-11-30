package nl.ramondevaan.adventofcode.day20;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
public class Particle {
  public final Identifier id;
  public final Vec3i position;
  public final Vec3i velocity;
  public final Vec3i acceleration;
}
