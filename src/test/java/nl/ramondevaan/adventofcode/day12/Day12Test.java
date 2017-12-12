package nl.ramondevaan.adventofcode.day12;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day12Test {
    private Day12 day12;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day12 != null) {
            return;
        }

        day12 = Day12.create(
                Paths.get(this.getClass().getResource(
                        "/Day12.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(115, day12.numInGroup0());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(221, day12.numberOfGroups());
    }
}
