package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class Score_Test extends Player_Test_Helper
{
    @Test
    public void test_winningUnscoredCardKeepsPointsAtZero() throws
        CantPlayException
    {
        Player firstPlayer = createPlayerInTurn();
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertEquals(0, firstPlayer.getScore());
    }

    @Test
    public void test_winningScoredCardChangesPoints() throws
        CantPlayException, CantPassException
    {
        Player firstPlayer = createSeededGame(100813);
        CardCollection cards = new CardCollection();
        for (Card card : firstPlayer.getCards())
        {
            cards.add(card);
        }

        firstPlayer.play(cards, Set.STRAIGHT);
        firstPlayer.getPlayerAtPositionCCW(1).pass();
        firstPlayer.getPlayerAtPositionCCW(2).pass();
        firstPlayer.getPlayerAtPositionCCW(3).pass();

        Assert.assertEquals(25, firstPlayer.getScore());
    }
}
