package nl.ramondevaan.adventofcode.day8;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day8Test {
    private Day8 day8;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day8 != null) {
            return;
        }

        day8 = Day8.create(
                Paths.get(this.getClass().getResource(
                        "/Day8.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(5752, day8.maximumValueAtEnd());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(6366, day8.maximumValueDuringExecution());
    }
}
