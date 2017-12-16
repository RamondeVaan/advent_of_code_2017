package nl.ramondevaan.adventofcode.day16;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Day16Test {
    private Day16 day16;

    @Before
    public void setUp() throws URISyntaxException, IOException {
        if(day16 != null) {
            return;
        }

        day16 = Day16.create(
                Paths.get(this.getClass().getResource(
                        "/Day16.txt"
                ).toURI()));
    }

    @Test
    public void exercise1() {
        Assert.assertEquals("ociedpjbmfnkhlga", day16.programOrder());
    }

    @Test
    public void exercise2() {
        Assert.assertEquals("gnflbkojhicpmead", day16.programOrderAfterBillion());
    }
}
