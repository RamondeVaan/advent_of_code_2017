package nl.ramondevaan.adventofcode.day08;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day08Test {
  private Day08 day08;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day08 != null) {
      return;
    }

    day08 = Day08.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day08.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(5752, day08.maximumValueAtEnd());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(6366, day08.maximumValueDuringExecution());
  }
}
