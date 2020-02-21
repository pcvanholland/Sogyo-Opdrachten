package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class Player_Test
{
    @Test
    public void test_init()
    {
        new Player();
    }

    @Test
    public void test_neighboursAreNotSelf()
    {
        Player firstPlayer = new Player();

        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(1));
        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(2));
        Assert.assertNotEquals(firstPlayer,
            firstPlayer.getPlayerAtPositionCCW(3));
    }

    @Test
    public void test_circleIsRound()
    {
        Player firstPlayer = new Player();

        Assert.assertEquals(firstPlayer, firstPlayer.getPlayerAtPositionCCW(4));
    }

    @Test
    public void test_playersStartsEmptyHanded()
    {
        Player firstPlayer = new Player();
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertEquals(0, firstPlayer.getPlayerAtPositionCCW(i).
                getCards().size());
        }
    }

    @Test
    public void test_playersStartsNotInTurn()
    {
        Player firstPlayer = new Player();
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertFalse(firstPlayer.getPlayerAtPositionCCW(i).
                isInTurn());
        }
    }

    @Test
    public void test_drawFirstCards()
    {
        Player firstPlayer = new Player();

        firstPlayer.drawCards();

        Assert.assertEquals(6, firstPlayer.getCards().size());
    }

    @Test
    public void test_drawSecondCards()
    {
        Player firstPlayer = new Player();

        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Assert.assertEquals(14, firstPlayer.getCards().size());
    }
}
