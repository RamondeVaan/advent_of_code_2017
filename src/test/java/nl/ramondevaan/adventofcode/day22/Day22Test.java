package nl.ramondevaan.adventofcode.day22;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Day22Test {
  private Day22 day22;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    final var path = Path.of(Objects.requireNonNull(Day22Test.class.getResource("/Day22.txt")).toURI());
    final var lines = Files.readAllLines(path);
    day22 = new Day22(lines);
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(5196, day22.exercise1());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(2511633, day22.exercise2());
  }

}