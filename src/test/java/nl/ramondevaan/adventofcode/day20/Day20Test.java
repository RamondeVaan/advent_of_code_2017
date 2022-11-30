package nl.ramondevaan.adventofcode.day20;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day20Test {
  private Day20 day20;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day20 != null) {
      return;
    }

    day20 = Day20.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day20.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(376, day20.closestParticleToZeroInTerm());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(574, day20.numberOfParticlesNotColliding());
  }
}
