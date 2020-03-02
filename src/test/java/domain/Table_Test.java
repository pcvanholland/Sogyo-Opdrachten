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
    public void test_tableStartsEmptyPlayed()
    {
        Table table = new Table();

        Assert.assertEquals(new ArrayList<Play>(), table.getCurrentPlays());
    }

    @Test
    public void test_currentTrickReturnsATrick() throws CantPlayTableException
    {
        Play play = Play_Test_Helper.createSingle(2);
        Table table = new Table();

        table.play(play);

        Assert.assertNotNull(table.getCurrentTrick());
    }

    @Test
    public void test_lastPlayReturnsPlay() throws CantPlayTableException
    {
        Play play = Play_Test_Helper.createSingle(2);
        Table table = new Table();

        table.play(play);

        Assert.assertEquals(play, table.getLastPlay());
    }

    @Test
    public void test_lastPlayReturnsLastPlay() throws CantPlayTableException
    {
        Play firstPlay = Play_Test_Helper.createSingle(2);
        Play secondPlay = Play_Test_Helper.createSingle(3);
        Table table = new Table();
        table.play(firstPlay);

        table.play(secondPlay);

        Assert.assertEquals(secondPlay, table.getLastPlay());
    }

    @Test
    public void test_currentPlaysReturnsAllPlays() throws
        CantPlayTableException
    {
        Play firstPlay = Play_Test_Helper.createSingle(2);
        Play secondPlay = Play_Test_Helper.createSingle(3);
        Table table = new Table();
        table.play(firstPlay);
        table.play(secondPlay);

        Assert.assertEquals(firstPlay, table.getCurrentPlays().get(0));
        Assert.assertEquals(secondPlay, table.getCurrentPlays().get(1));
    }

    @Test
    public void test_canPlayWithoutLead()
    {
        Play play = Play_Test_Helper.createSingle(2);
        Table table = new Table();

        Assert.assertTrue(table.canPlay(play));
    }

    @Test
    public void test_canPlayWithLead() throws CantPlayTableException
    {
        Play firstPlay = Play_Test_Helper.createSingle(2);
        Play secondPlay = Play_Test_Helper.createSingle(5);
        Table table = new Table();
        table.play(firstPlay);

        Assert.assertTrue(table.canPlay(secondPlay));
    }

    @Test
    public void test_cantPlayCardThatDoesntBeat() throws CantPlayTableException
    {
        Play lowPlay = Play_Test_Helper.createSingle(2);
        Play highPlay = Play_Test_Helper.createSingle(5);
        Table table = new Table();

        table.play(highPlay);

        Assert.assertFalse(table.canPlay(lowPlay));
    }

    @Test(expected = CantPlayTableException.class)
    public void test_reallyCantPlayCardThatDoesntBeat() throws
        CantPlayTableException
    {
        Play lowPlay = Play_Test_Helper.createSingle(2);
        Play highPlay = Play_Test_Helper.createSingle(5);
        Table table = new Table();
        table.play(highPlay);

        table.play(lowPlay);
    }
}
