package nl.ramondevaan.adventofcode.day10;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day10Test {
    private Day10 day10;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day10 != null) {
            return;
        }

        day10 = Day10.create(
                Paths.get(this.getClass().getResource(
                        "/Day10.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(54675, day10.multiplyFirstTwoNumbers());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals("A7AF2706AA9A09CF5D848C1E6605DD2A", day10.fullHash());
    }
}
