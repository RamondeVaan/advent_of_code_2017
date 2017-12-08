package nl.ramondevaan.adventofcode.day1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day1Test {
    private Day1 day1;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day1 != null) {
            return;
        }

        day1 = Day1.create(
                Paths.get(this.getClass().getResource(
                        "/Day1.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(1049, day1.sumIfPairs(1));
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(1508, day1.sumIfPairs(day1.getValues().size() / 2));
    }
}
