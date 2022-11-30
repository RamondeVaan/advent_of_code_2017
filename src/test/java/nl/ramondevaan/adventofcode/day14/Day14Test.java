package nl.ramondevaan.adventofcode.day14;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day14Test {
  private Day14 day14;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day14 != null) {
      return;
    }

    day14 = Day14.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day14.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(8316, day14.usedSquares());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(1074, day14.numberOfGroups());
  }
}
