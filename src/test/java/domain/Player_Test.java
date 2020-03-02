package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class Player_Test
{
    private static final int SEED = 1;

    @Test
    public void test_init()
    {
        new Player(new Table());
    }

    @Test
    public void test_neighboursAreNotSelf()
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
    public void test_circleIsRound()
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertEquals(firstPlayer, firstPlayer.getPlayerAtPositionCCW(4));
    }

    @Test
    public void test_playersStartsEmptyHanded()
    {
        Player firstPlayer = new Player(new Table());
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertEquals(0, firstPlayer.getPlayerAtPositionCCW(i).
                getCards().size());
        }
    }

    @Test
    public void test_playersStartsNotInTurn()
    {
        Player firstPlayer = new Player(new Table());
        for (int i = 0; i < 4; ++i)
        {
            Assert.assertFalse(firstPlayer.getPlayerAtPositionCCW(i).
                isInTurn());
        }
    }

    @Test
    public void test_zeroHandsDrawn()
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertTrue(firstPlayer.canDrawCards());
    }

    @Test
    public void test_drawFirstCards()
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();

        Assert.assertEquals(6, firstPlayer.getCards().size());
    }

    @Test
    public void test_drawFirstCardsAreSeeded()
    {
        Dealer refDealer = new Dealer(SEED);
        Player firstPlayer = new Player(new Table(), SEED);

        firstPlayer.drawCards();

        int i = 0;
        for (Card card : refDealer.drawFirstHand())
        {
            Assert.assertTrue(
                card.getSuit() == firstPlayer.getCards().get(i).getSuit() &&
                card.getRank() == firstPlayer.getCards().get(i).getRank()
            );
            i++;
        }
    }

    @Test
    public void test_canDrawSecondHandOfCards()
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();

        Assert.assertTrue(firstPlayer.canDrawCards());
    }

    @Test
    public void test_drawSecondCards()
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Assert.assertEquals(14, firstPlayer.getCards().size());
    }

    @Test
    public void test_cantDrawThirdHandOfCards()
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Assert.assertFalse(firstPlayer.canDrawCards());
    }

    @Test
    public void test_cantDrawToManyCards()
    {
        Player firstPlayer = new Player(new Table());

        firstPlayer.drawCards();
        firstPlayer.drawCards();
        firstPlayer.drawCards();

        Assert.assertEquals(14, firstPlayer.getCards().size());
    }

    @Test
    public void test_cantPlayWhenOutOfTurn()
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertFalse(
            firstPlayer.canPlay(Play_Test_Helper.createSingle(2))
        );
    }

    @Test
    public void test_canPlayBombWhenOutOfTurn()
    {
        Player firstPlayer = new Player(new Table());

        Assert.assertTrue(
            firstPlayer.canPlay(Play_Test_Helper.createFOAKBomb(2))
        );
    }

    @Test
    public void test_play()
    {
        Table playingTable = new Table();
        Player firstPlayer = new Player(playingTable);
        Play play = Play_Test_Helper.createSingle(2);

        firstPlayer.play(play);

        Assert.assertEquals(play, playingTable.getLastPlay());
    }
}
