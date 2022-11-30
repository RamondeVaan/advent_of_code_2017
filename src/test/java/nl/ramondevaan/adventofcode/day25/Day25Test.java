package nl.ramondevaan.adventofcode.day25;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Day25Test {
  private Day25 day25;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    final var path = Path.of(Objects.requireNonNull(Day25Test.class.getResource("/Day25.txt")).toURI());
    final var lines = Files.readAllLines(path);
    day25 = new Day25(lines);
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(4225, day25.exercise1());
  }

}