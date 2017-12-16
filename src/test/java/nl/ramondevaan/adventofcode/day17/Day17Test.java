package nl.ramondevaan.adventofcode.day17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day17Test {
    private Day17 day17;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day17 != null) {
            return;
        }

        day17 = Day17.create(
                Paths.get(this.getClass().getResource(
                        "/Day17.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
//        Assert.assertEquals("ociedpjbmfnkhlga", day17.programOrder());
    }

    @Test
    public void exercise2() {
//        Assert.assertEquals("gnflbkojhicpmead", day17.programOrderAfterBillion());
    }
}
