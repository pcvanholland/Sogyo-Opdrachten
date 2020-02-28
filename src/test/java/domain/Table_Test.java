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

        table.lead(play);

        Assert.assertNotNull(table.getCurrentTrick());
    }

    @Test
    public void test_lastPlayReturnsPlay()
    {
        Play play = Play_Test.createSingle(2);
        Table table = new Table();

        table.lead(play);

        Assert.assertEquals(play, table.getLastPlay());
    }

    @Test
    public void test_lastPlayReturnsLastPlay()
    {
        Play firstPlay = Play_Test.createSingle(2);
        Play secondPlay = Play_Test.createSingle(2);
        Table table = new Table();
        table.lead(firstPlay);

        table.play(secondPlay);

        Assert.assertEquals(secondPlay, table.getLastPlay());
    }
}
