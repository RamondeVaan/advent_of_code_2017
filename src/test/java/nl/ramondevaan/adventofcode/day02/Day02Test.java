package nl.ramondevaan.adventofcode.day02;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day02Test {
  private Day02 day02;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day02 != null) {
      return;
    }

    day02 = Day02.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day02.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(36174, day02.checksum());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(244, day02.divisibleChecksum());
  }
}
