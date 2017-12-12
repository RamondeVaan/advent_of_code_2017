package nl.ramondevaan.adventofcode.day06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day06Test {
    private Day06 day06;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day06 != null) {
            return;
        }

        day06 = Day06.create(
                Paths.get(this.getClass().getResource(
                        "/Day06.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(5042, day06.numberOfSteps());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(1086, day06.cycleSize());
    }
}
