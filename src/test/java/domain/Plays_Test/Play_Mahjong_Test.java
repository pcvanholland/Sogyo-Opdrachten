package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Mahjong_Test extends Play_Test_Helper
{
    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.MAHJONG));

        new Single(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValiditySingle()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.MAHJONG));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.SINGLE);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_mahjongBeatenByAllSingles()
    {
        Play firstPlay = createMahjong();
        Play secondPlay;
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            secondPlay = createSingle(i);
            Assert.assertTrue(secondPlay.beats(firstPlay));
        }
    }

    @Test
    public void test_mahjongCantBeatAllSingles()
    {
        Play firstPlay;
        Play secondPlay = createMahjong();
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            firstPlay = createSingle(i);
            Assert.assertFalse(secondPlay.beats(firstPlay));
        }
    }

    @Test
    public void test_mahjongFitsInStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.MAHJONG));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_mahjongDoesntFitInHigherStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.MAHJONG));
        cards.add(createRandomCard(6));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_mahjongCantBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.MAHJONG));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }
}
