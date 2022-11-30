package nl.ramondevaan.adventofcode.day07;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day07Test {
  private Day07 day07;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day07 != null) {
      return;
    }

    day07 = Day07.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day07.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals("hmvwl", day07.rootName());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(1853, day07.unbalanced());
  }
}
