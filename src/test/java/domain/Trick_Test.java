package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Trick_Test
{
    @Test
    public void test_init()
    {
        new Trick(Play_Test.createSingle(2));
    }

    @Test
    public void test_getLastPlayReturnsPlay()
    {
        Play play = Play_Test.createSingle(2);

        Trick trick = new Trick(play);

        Assert.assertEquals(play, trick.getLastPlay());
    }

    @Test
    public void test_getLastPlayReturnsLastPlay()
    {
        Play firstPlay = Play_Test.createSingle(2);
        Play secondPlay = Play_Test.createSingle(3);
        Trick trick = new Trick(firstPlay);

        trick.play(secondPlay);

        Assert.assertEquals(secondPlay, trick.getLastPlay());
    }
}
