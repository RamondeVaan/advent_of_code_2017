package nl.ramondevaan.adventofcode.day05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day05Test {
    private Day05 day05;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day05 != null) {
            return;
        }

        day05 = Day05.create(
                Paths.get(this.getClass().getResource(
                        "/Day05.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(355965, day05.numberOfSteps());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(26948068, day05.numberOfStepsComplex());
    }
}
