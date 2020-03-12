package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class Pass_Test extends Player_Test_Helper
{
    @Test
    public void test_passTurnsPlayerOutOfTurn() throws TaiPanException
    {
        Player firstPlayer = this.createSeededGame(START_SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        firstPlayer.play(cards, Set.SINGLE);

        firstPlayer.getPlayerAtPositionCCW(1).pass();

        Assert.assertFalse(
            firstPlayer.getPlayerAtPositionCCW(1).isInTurn()
        );
    }

    @Test(expected = CantPassException.class)
    public void test_cantPassWhenOutOfTurn() throws TaiPanException
    {
        Player firstPlayer = this.createPlayerInTurn();

        firstPlayer.getPlayerAtPositionCCW(1).pass();
    }

    @Test(expected = CantPassException.class)
    public void test_cantPassWhenLeading() throws TaiPanException
    {
        Player firstPlayer = this.createPlayerInTurn();

        firstPlayer.pass();
    }

    @Test
    public void test_allPassTakesTrick() throws TaiPanException
    {
        Player firstPlayer = this.createSeededGame(START_SEED);
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        firstPlayer.play(cards, Set.SINGLE);
        firstPlayer.getPlayerAtPositionCCW(1).pass();
        firstPlayer.getPlayerAtPositionCCW(2).pass();
        firstPlayer.getPlayerAtPositionCCW(3).pass();

        Assert.assertEquals(1, firstPlayer.getWonTricks().size());
    }
}
