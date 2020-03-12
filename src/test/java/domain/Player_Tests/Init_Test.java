package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Init_Test extends Player_Test_Helper
{
    @Test
    public void test_init() throws TaiPanException
    {
        new Player(new Table());
    }

    @Test
    public void test_neighboursAreNotSelf() throws TaiPanException
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(1));
        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(2));
        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(3));
    }

    @Test
    public void test_circleIsRound() throws TaiPanException
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertEquals(firstPlayer, firstPlayer.getPlayerAtPositionCCW(4));
    }

    @Test(expected = InvalidPositionException.class)
    public void test_cantAskNegativePos() throws TaiPanException
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.getPlayerAtPositionCCW(-1);
    }

    @Test
    public void test_playersStartsEmptyHanded() throws TaiPanException
    {
        Player firstPlayer = new Player(new Table());
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertEquals(0, firstPlayer.getPlayerAtPositionCCW(i).
                getCards().size());
        }
    }

    @Test
    public void test_playersStartNotInTurn() throws TaiPanException
    {
        Player firstPlayer = new Player(new Table());
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertFalse(firstPlayer.getPlayerAtPositionCCW(i).
                isInTurn());
        }
    }

    @Test
    public void test_playersStartWithoutWonTricks() throws TaiPanException
    {
        Player firstPlayer = new Player(new Table());

        for (int i = 0; i < 4; ++i)
        {
            Assert.assertEquals(
                new ArrayList<Trick>(),
                firstPlayer.getPlayerAtPositionCCW(i).getWonTricks()
            );
        }
    }

    @Test
    public void test_playersBeginWithZeroPoints() throws TaiPanException
    {
        Player firstPlayer = new Player(new Table());

        for (int i = 0; i < 4; ++i)
        {
            Assert.assertEquals(
                0,
                firstPlayer.getPlayerAtPositionCCW(i).getScore()
            );
        }
    }
}
