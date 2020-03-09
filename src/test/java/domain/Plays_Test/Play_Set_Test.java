package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Set_Test extends Play_Test_Helper
{
    @Test
    public void test_playValidityPair()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.PAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInvalidityNonSet()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playValidityTriple()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.TRIPLE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInvalidityTriple()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInvalidityTripleWithTwoEqual()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playValidityBomb()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.BOMB);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInvalidityBomb()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInvalidityBombWithTwoEqual()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInvalidityBombWithThreeEqual()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }
}
