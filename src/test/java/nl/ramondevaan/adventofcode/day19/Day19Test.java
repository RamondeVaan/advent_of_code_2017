package nl.ramondevaan.adventofcode.day19;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day19Test {
  private Day19 day19;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day19 != null) {
      return;
    }

    day19 = Day19.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day19.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals("VEBTPXCHLI", day19.lettersFound());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(18702, day19.stepsTaken());
  }
}
