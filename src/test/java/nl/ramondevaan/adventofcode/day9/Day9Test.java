package nl.ramondevaan.adventofcode.day9;

import nl.ramondevaan.adventofcode.day8.Day8;
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
        System.out.println(day9.groupScore());
//        Assert.assertEquals(5752, day9.maximumValueAtEnd());
    }

    @Test
    public void exercise2() {
//        Assert.assertEquals(6366, day9.maximumValueDuringExecution());
    }
}
