package nl.ramondevaan.adventofcode.day24;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Day24Test {
  private Day24 day24;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    final var path = Path.of(Objects.requireNonNull(Day24Test.class.getResource("/Day24.txt")).toURI());
    final var lines = Files.readAllLines(path);
    day24 = new Day24(lines);
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(1906, day24.exercise1());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(1824, day24.exercise2());
  }

}