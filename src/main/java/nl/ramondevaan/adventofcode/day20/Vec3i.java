package nl.ramondevaan.adventofcode.day20;

import lombok.Data;

import java.util.Arrays;

@Data
public class Vec3i {
  public final int x;
  public final int y;
  public final int z;

  public static Vec3i parse(String... s) {
    if (s.length != 3) {
      throw new IllegalArgumentException(String.format(
          "Vec3i parsing failure: string array \"%s\" did not contain 3 elements",
          Arrays.toString(s)
      ));
    }

    return new Vec3i(
        Integer.parseInt(s[0]),
        Integer.parseInt(s[1]),
        Integer.parseInt(s[2])
    );
  }

  public static int manhattanDistance(Vec3i v, Vec3i w) {
    return Math.abs(v.x - w.x) + Math.abs(v.y - w.y) + Math.abs(v.z - w.z);
  }
}
