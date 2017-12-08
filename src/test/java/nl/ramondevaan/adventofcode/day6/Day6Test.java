package nl.ramondevaan.adventofcode.day6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day6Test {
    private Day6 day6;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day6 != null) {
            return;
        }

        day6 = Day6.create(
                Paths.get(this.getClass().getResource(
                        "/Day6.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(5042, day6.numberOfSteps());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(1086, day6.cycleSize());
    }
}
