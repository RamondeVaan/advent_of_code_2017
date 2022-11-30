package nl.ramondevaan.adventofcode.day23;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Day23Test {
  private Day23 day23;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    final var path = Path.of(Objects.requireNonNull(Day23Test.class.getResource("/Day23.txt")).toURI());
    final var lines = Files.readAllLines(path);
    day23 = new Day23(lines);
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(3969, day23.exercise1());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(917, day23.exercise2());
  }

}