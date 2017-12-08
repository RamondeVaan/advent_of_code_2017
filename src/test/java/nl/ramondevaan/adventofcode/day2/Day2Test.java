package nl.ramondevaan.adventofcode.day2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day2Test {
    private Day2 day2;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day2 != null) {
            return;
        }

        day2 = Day2.create(
                Paths.get(this.getClass().getResource(
                        "/Day2.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(36174, day2.checksum());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(244, day2.divisibleChecksum());
    }
}
