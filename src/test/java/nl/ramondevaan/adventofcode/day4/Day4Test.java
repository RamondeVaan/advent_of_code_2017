package nl.ramondevaan.adventofcode.day4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day4Test {
    private Day4 day4;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day4 != null) {
            return;
        }

        day4 = Day4.create(
                Paths.get(this.getClass().getResource(
                        "/Day4.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(466, day4.validPassphrasesNoDuplicates());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(251, day4.validPassphrasesNoAnagrams());
    }
}
