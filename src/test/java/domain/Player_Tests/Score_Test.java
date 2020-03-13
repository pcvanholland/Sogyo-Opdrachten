package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

public class Score_Test extends Player_Test_Helper
{
    @Test
    public void test_winningUnscoredCardKeepsPointsAtZero() throws
        TaiPanException
    {
        Player firstPlayer = createPlayerInTurn();
        firstPlayer.getPlayerAtPositionCCW(1).drawCards();
        CardCollection cards = new CardCollection();

        // A Card we are certain the Player in turn has.
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        firstPlayer.play(cards, Set.SINGLE);

        Assert.assertEquals(0, firstPlayer.getScore());
    }

    @Test
    public void test_winningScoredCardChangesPoints() throws TaiPanException
    {
        int result = 0;
        Player firstPlayer = createSeededPlayer(START_STREET_SEED);
        firstPlayer.getPlayerAtPositionCCW(1).drawCards();
        CardCollection cards = new CardCollection();
        for (Card card : firstPlayer.getCards())
        {
            cards.add(card);
            result += card.getScore();
        }

        firstPlayer.play(cards, Set.STRAIGHT);

        Assert.assertEquals(result, firstPlayer.getScore());
    }

    @Test
    public void test_winningScoredCardChangesPointsIncludingPhoenix() throws
        TaiPanException
    {
        int result = 0;
        Player firstPlayer = createSeededPlayer(START_STREET_PHOENIX_SEED);
        firstPlayer.getPlayerAtPositionCCW(1).drawCards();
        CardCollection cards = new CardCollection();
        for (Card card : firstPlayer.getCards())
        {
            cards.add(card);
            result += card.getScore();
        }

        firstPlayer.play(cards, Set.STRAIGHT);

        Assert.assertEquals(result, firstPlayer.getScore());
    }
}
