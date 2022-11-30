package nl.ramondevaan.adventofcode.day09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day09Test {
  private Day09 day09;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day09 != null) {
      return;
    }

    day09 = Day09.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day09.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(10820, day09.groupScore());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(5547, day09.numberOfCharacters());
  }
}
