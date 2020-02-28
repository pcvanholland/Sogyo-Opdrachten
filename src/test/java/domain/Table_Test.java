package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Table_Test
{
    @Test
    public void test_init()
    {
        new Table();
    }

    @Test
    public void test_tableStartsEmpty()
    {
        Table table = new Table();

        Assert.assertNull(table.getCurrentTrick());
    }

    @Test
    public void test_currentTrickReturnsATrick()
    {
        Play play = Play_Test.createSingle(2);
        Table table = new Table();

        table.play(play);

        Assert.assertNotNull(table.getCurrentTrick());
    }

    @Test
    public void test_lastPlayReturnsPlay()
    {
        Play play = Play_Test.createSingle(2);
        Table table = new Table();

        table.play(play);

        Assert.assertEquals(play, table.getLastPlay());
    }

    @Test
    public void test_lastPlayReturnsLastPlay()
    {
        Play firstPlay = Play_Test.createSingle(2);
        Play secondPlay = Play_Test.createSingle(2);
        Table table = new Table();
        table.play(firstPlay);

        table.play(secondPlay);

        Assert.assertEquals(secondPlay, table.getLastPlay());
    }

    @Test
    public void test_canPlayWithoutLead()
    {
        Play play = Play_Test.createSingle(2);
        Table table = new Table();

        Assert.assertTrue(table.canPlay(play));
    }

    @Test
    public void test_canPlayWithLead()
    {
        Play firstPlay = Play_Test.createSingle(2);
        Play secondPlay = Play_Test.createSingle(5);
        Table table = new Table();
        table.play(firstPlay);

        Assert.assertTrue(table.canPlay(secondPlay));
    }

    @Test
    public void test_cantPlayCardThatDoesntBeat()
    {
        Play lowPlay = Play_Test.createSingle(2);
        Play highPlay = Play_Test.createSingle(5);
        Table table = new Table();

        table.play(highPlay);

        Assert.assertFalse(table.canPlay(lowPlay));
    }
}
