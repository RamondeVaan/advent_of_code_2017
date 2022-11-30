package nl.ramondevaan.adventofcode.day04;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

public class Day04Test {
  private Day04 day04;

  @Before
  public void setUp() throws URISyntaxException, IOException {
    if (day04 != null) {
      return;
    }

    day04 = Day04.create(Paths.get(Objects.requireNonNull(this.getClass().getResource("/Day04.txt")).toURI()));
  }

  @Test
  public void exercise1() {
    Assert.assertEquals(466, day04.validPassphrasesNoDuplicates());
  }

  @Test
  public void exercise2() {
    Assert.assertEquals(251, day04.validPassphrasesNoAnagrams());
  }
}
