package nl.ramondevaan.adventofcode.day18;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day18Test {
    private Day18 day18;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day18 != null) {
            return;
        }

        day18 = Day18.create(
                Paths.get(this.getClass().getResource(
                        "/Day18.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(3423, day18.recoveredFrequency());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(7493, day18.messagesSent());
    }
}
