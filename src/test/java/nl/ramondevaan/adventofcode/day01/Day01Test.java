package nl.ramondevaan.adventofcode.day01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day01Test {
  private Day01 day01;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day01 != null) {
      return;
    }

    day01 = Day01.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day01.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(1049, day01.sumIfPairs(1));
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(1508, day01.sumIfPairs(day01.getValues().size() / 2));
  }
}
