package nl.ramondevaan.adventofcode.day9;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day9Test {
    private Day9 day9;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day9 != null) {
            return;
        }

        day9 = Day9.create(
                Paths.get(this.getClass().getResource(
                        "/Day9.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(10820, day9.groupScore());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(5547, day9.numberOfCharacters());
    }
}
