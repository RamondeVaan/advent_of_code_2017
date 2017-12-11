package nl.ramondevaan.adventofcode.day11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day11Test {
    private Day11 day11;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day11 != null) {
            return;
        }

        day11 = Day11.create(
                Paths.get(this.getClass().getResource(
                        "/Day11.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(747, day11.distance());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(1544, day11.furthestDistance());
    }
}
