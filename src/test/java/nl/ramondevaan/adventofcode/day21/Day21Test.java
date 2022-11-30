package nl.ramondevaan.adventofcode.day21;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Day21Test {
  private Day21 day21;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    final var path = Path.of(Objects.requireNonNull(Day21Test.class.getResource("/Day21.txt")).toURI());
    final var lines = Files.readAllLines(path);
    day21 = new Day21(lines);
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(190, day21.exercise1());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(2335049, day21.exercise2());
  }

}