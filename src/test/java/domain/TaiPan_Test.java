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
        new TaiPan(Player_Test_Helper.START_SEED);
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
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID + 1);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(13, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayNotInTurn()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        int amount = tp.getPlayer(playerID).getCards().size();

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(amount, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayNotInTurnOtherPlayer()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID + 1, cards, Set.SINGLE);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayHasNoCards()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan();

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(0, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayInvalidPlay()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.PAIR);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_cantPlayDontHasCards()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        tp.play(playerID, cards, Set.SINGLE);

        Assert.assertEquals(14, tp.getPlayer(playerID).getCards().size());
    }

    @Test
    public void test_pass()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID + 1);
        tp.letPlayerDrawCards(playerID + 1);
        tp.letPlayerDrawCards(playerID + 2);
        tp.letPlayerDrawCards(playerID + 2);
        tp.letPlayerDrawCards(playerID + 3);
        tp.letPlayerDrawCards(playerID + 3);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        tp.play(playerID, cards, Set.SINGLE);

        tp.pass(playerID + 1);

        Assert.assertFalse(tp.getPlayer(playerID).isInTurn());
        Assert.assertFalse(tp.getPlayer(playerID + 1).isInTurn());
        Assert.assertTrue(tp.getPlayer(playerID + 2).isInTurn());
    }

    @Test
    public void test_cantPassWhenNotInTurn()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test_Helper.START_SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        tp.pass(playerID + 1);

        Assert.assertTrue(tp.getPlayer(playerID).isInTurn());
        Assert.assertFalse(tp.getPlayer(playerID + 1).isInTurn());
    }
}
