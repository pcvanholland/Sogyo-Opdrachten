package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TaiPan_Test
{
    @Test
    public void test_init()
    {
        new TaiPan();
        new TaiPan(Player_Test.SEED);
    }

    @Test
    public void test_drawCards()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_drawTooMuchCards()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_play()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test.SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        tp.play(playerID, "[\"SPECIAL,MAHJONG\"]", "SINGLE");
        Assert.assertEquals(13, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayNotInTurn()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test.SEED);
        tp.letPlayerDrawCards(playerID);

        tp.play(playerID, "[\"SPECIAL,MAHJONG\"]", "SINGLE");
        Assert.assertEquals(6, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayNotInTurnOtherPlayer()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test.SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        tp.play(1, "[\"SPECIAL,MAHJONG\"]", "SINGLE");
        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayHasNoCards()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();

        tp.play(playerID, "[\"SPECIAL,MAHJONG\"]", "SINGLE");
        Assert.assertEquals(0, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayInvalidPlay()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        tp.play(playerID, "[\"SPECIAL,MAHJONG\"]", "PAIR");
        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_pass()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test.SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);
        tp.play(playerID, "[\"SPECIAL,MAHJONG\"]", "SINGLE");

        tp.pass(playerID + 1);

        Assert.assertFalse(tp.getPlayer(playerID).isInTurn());
        Assert.assertFalse(tp.getPlayer(playerID + 1).isInTurn());
        Assert.assertTrue(tp.getPlayer(playerID + 2).isInTurn());
    }

    @Test
    public void test_cantPassWhenNotInTurn()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test.SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        tp.pass(playerID + 1);

        Assert.assertTrue(tp.getPlayer(playerID).isInTurn());
        Assert.assertFalse(tp.getPlayer(playerID + 1).isInTurn());
    }
}
