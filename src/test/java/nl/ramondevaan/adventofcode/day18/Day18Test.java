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
//        Assert.assertEquals(596, day18.valueAfter2017());
    }

    @Test
    public void exercise2() {
//        Assert.assertEquals(39051595, day18.valueAfter0());
    }
}
