package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Stair_Test extends Play_Test_Helper
{
    @Test
    public void test_playInValidityOddSizedArray()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.THREE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityDoubleSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityDoubleSetAddedOrderDoesNotMatter()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));

        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleSetNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityTripleSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));

        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityTripleSetAddedOrderDoesNotMatter()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));

        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleSetWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.THREE));

        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityTripleSetSlightlyNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));

        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FIVE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityTripleSetNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.FOUR));

        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.SIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityLongSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (StandardRank rank : StandardRank.values())
        {
            cards.add(new PlayingCard(StandardSuit.SWORD, rank));
            cards.add(new PlayingCard(StandardSuit.JADE, rank));
        }

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));

        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));

        new Stair(cards);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));

        new Stair(cards);
    }

    @Test(expected = InvalidStairException.class)
    public void test_initFailsWhenTriedWithDifferentType()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));

        new Stair(cards);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createStair(2, 3);
        Play secondPlay = createStair(3, 4);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createStair(2, 3);
        Play secondPlay = createStair(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createStair(3, 4);
        Play secondPlay = createStair(2, 3);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_stairNotBeatSingle()
    {
        Play firstPlay = createStair(3, 4);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
