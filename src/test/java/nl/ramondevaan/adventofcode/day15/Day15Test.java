package nl.ramondevaan.adventofcode.day15;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day15Test {
    private Day15 day15;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day15 != null) {
            return;
        }

        day15 = Day15.create(
                Paths.get(this.getClass().getResource(
                        "/Day15.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(650, day15.countMatches());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(336, day15.countMatchesCriteria());
    }
}
