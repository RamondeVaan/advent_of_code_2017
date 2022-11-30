package nl.ramondevaan.adventofcode.day17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day17Test {
  private Day17 day17;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day17 != null) {
      return;
    }

    day17 = Day17.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day17.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(596, day17.valueAfter2017());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(39051595, day17.valueAfter0());
  }
}
