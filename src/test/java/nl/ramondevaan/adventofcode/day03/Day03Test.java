package nl.ramondevaan.adventofcode.day03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day03Test {
    private Day03 day03;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day03 != null) {
            return;
        }

        day03 = Day03.create(
                Paths.get(this.getClass().getResource(
                        "/Day03.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(438, day03.spiralManhattanDistance());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(266330, day03.stressTest());
    }
}
