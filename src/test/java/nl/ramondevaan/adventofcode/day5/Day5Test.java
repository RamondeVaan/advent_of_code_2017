package nl.ramondevaan.adventofcode.day5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day5Test {
    private Day5 day5;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day5 != null) {
            return;
        }

        day5 = Day5.create(
                Paths.get(this.getClass().getResource(
                        "/Day5.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals(355965, day5.numberOfSteps());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals(26948068, day5.numberOfStepsComplex());
    }
}
