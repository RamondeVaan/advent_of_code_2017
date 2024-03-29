package nl.ramondevaan.adventofcode.day13;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day13Test {
  private Day13 day13;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day13 != null) {
      return;
    }

    day13 = Day13.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day13.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(1624, day13.calculateSeverity());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(3923436, day13.findDelay());
  }
}
