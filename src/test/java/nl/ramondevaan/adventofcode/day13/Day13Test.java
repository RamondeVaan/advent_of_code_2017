package nl.ramondevaan.adventofcode.day13;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day13Test {
    private Day13 day13;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day13 != null) {
            return;
        }

        day13 = Day13.create(
                Paths.get(this.getClass().getResource(
                        "/Day13.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        System.out.println(day13.exercise1());
//        Assert.assertEquals(115, day13.exercise1());
    }

    @Test
    public void exercise2() {
        System.out.println(day13.exercise2());
//        Assert.assertEquals(221, day13.exercise2());
    }
}
