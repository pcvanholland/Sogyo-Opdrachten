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
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityDoubleSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityDoubleSetAddedOrderDoesNotMatter()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleSetNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityTripleSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityTripleSetAddedOrderDoesNotMatter()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityDoubleSetWithTwoSingles()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        cards.add(createRandomCard(5));
        cards.add(createRandomCard(4));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityTripleSetSlightlyNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        cards.add(createRandomCard(5));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playInvalidityTripleSetNotInOrder()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        cards.add(createRandomCard(6));
        cards.add(createRandomCard(6));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityLongSet()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (StandardRank rank : StandardRank.values())
        {
            cards.add(createRandomCard(rank.getValue()));
            cards.add(createRandomCard(rank.getValue()));
        }

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, PlayHelper.determineTypesOfSet(cards));
    }

    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        new Stair(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));

        new Stair(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidStairException.class)
    public void test_initFailsWhenTriedWithDifferentType()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        new Stair(cards, TEST_PLAYER);
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
