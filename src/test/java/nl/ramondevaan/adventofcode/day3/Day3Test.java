package nl.ramondevaan.adventofcode.day3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day3Test {
    private Day3 day3;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day3 != null) {
            return;
        }

        day3 = Day3.create(
                Paths.get(this.getClass().getResource(
                        "/Day3.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(438, day3.spiralManhattanDistance());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(266330, day3.stressTest());
    }
}
