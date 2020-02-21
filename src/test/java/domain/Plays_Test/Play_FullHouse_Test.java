package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_FullHouse_Test extends Play_Test
{
    @Test
    public void test_init()
    {
        new FullHouse(createTriple(2), createPair(3));
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createFullHouse(2, 3);
        Play secondPlay = createFullHouse(3, 4);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_pairDoesNotMatterToLose()
    {
        Play firstPlay = createFullHouse(3, 2);
        Play secondPlay = createFullHouse(2, 4);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_pairDoesNotMatterToWin()
    {
        Play firstPlay = createFullHouse(3, 4);
        Play secondPlay = createFullHouse(4, 2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createFullHouse(2, 3);
        Play secondPlay = createFullHouse(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createFullHouse(3, 4);
        Play secondPlay = createFullHouse(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_fullHouseNotBeatSingle()
    {
        Play firstPlay = createFullHouse(3, 4);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
