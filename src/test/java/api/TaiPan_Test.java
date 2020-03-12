package taipan.api;

import org.junit.Assert;
import org.junit.Test;

public class TaiPan_Test
{
    @Test
    public void test_init()
    {
        TaiPan tp = new TaiPan("");
    }

    @Test
    public void test_newGameMakesTaiPan()
    {
        TaiPan tp = new TaiPan("");

        Assert.assertTrue(
            tp.getImplementation() instanceof taipan.domain.TaiPan
        );
    }

    @Test
    public void test_getHostName()
    {
        String hostname = "HostName";
        TaiPan tp = new TaiPan(hostname);

        Assert.assertEquals(hostname, tp.getHostName());
    }

    @Test
    public void test_joinGame()
    {
        TaiPan tp = new TaiPan("");

        Assert.assertEquals(1, tp.joinGame(""));
    }

    @Test
    public void test_gameStartsNotFull()
    {
        TaiPan tp = new TaiPan("");

        Assert.assertFalse(tp.isFull());
    }

    @Test
    public void test_gameGetsFull()
    {
        TaiPan tp = new TaiPan("");
        tp.joinGame("");
        tp.joinGame("");
        tp.joinGame("");

        Assert.assertTrue(tp.isFull());
    }

    @Test
    public void test_fullGameJoinBecomesObserver()
    {
        TaiPan tp = new TaiPan("");
        tp.joinGame("");
        tp.joinGame("");
        tp.joinGame("");

        Assert.assertEquals(-1, tp.joinGame(""));
    }
}
