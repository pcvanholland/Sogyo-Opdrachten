package taipan.api;

import org.junit.Assert;
import org.junit.Test;

public class Game_Test
{
    @Test
    public void test_init()
    {
        Game tp = new Game("");
    }

    @Test
    public void test_newGameMakesTaiPan()
    {
        Game tp = new Game("");

        Assert.assertTrue(
            tp.getImplementation() instanceof taipan.domain.TaiPan
        );
    }

    @Test
    public void test_getHostName()
    {
        String hostname = "HostName";
        Game tp = new Game(hostname);

        Assert.assertEquals(hostname, tp.getHostName());
    }

    @Test
    public void test_joinGame()
    {
        Game tp = new Game("");

        Assert.assertEquals(1, tp.joinGame(""));
    }

    @Test
    public void test_gameStartsNotFull()
    {
        Game tp = new Game("");

        Assert.assertFalse(tp.isFull());
    }

    @Test
    public void test_gameGetsFull()
    {
        Game tp = new Game("");
        tp.joinGame("");
        tp.joinGame("");
        tp.joinGame("");

        Assert.assertTrue(tp.isFull());
    }

    @Test
    public void test_fullGameJoinBecomesObserver()
    {
        Game tp = new Game("");
        tp.joinGame("");
        tp.joinGame("");
        tp.joinGame("");

        Assert.assertEquals(-1, tp.joinGame(""));
    }
}
