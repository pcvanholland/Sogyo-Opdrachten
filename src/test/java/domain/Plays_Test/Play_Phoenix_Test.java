package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Phoenix_Test
{
    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));

        new Single(cards);
    }

    @Test
    public void test_playValiditySingle()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.SINGLE);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }
/*
    @Test
    public void test_phoenixCanBePartOfPair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.PAIR);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixCanBePartOfTriple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.TRIPLE);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixCanBePartOfFullHouse()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixCanBePartOfStair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixCanBePartOfStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixCantBePartOfLargestStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            cards.add(createRandomCard(i));
        }

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixCantBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixCantBombStraight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_playValidityFullHouseOfFiveEquals()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, Play.determineTypesOfSet(cards));
    }

    @Test
    public void test_phoenixBeatsAllStandardSingles()
    {
        Play firstPlay;
        Play secondPlay = createPhoenix();
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            firstPlay = createSingle(i);
            Assert.assertTrue(secondPlay.beats(firstPlay));
        }
    }

    @Test
    public void test_phoenixIsBeatenByAllStandardSingles()
    {
        Play firstPlay = createDragon();
        Play secondPlay;
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            secondPlay = createSingle(i);
            Assert.assertTrue(secondPlay.beats(firstPlay));
        }
    }

    @Test
    public void test_phoenixCantBeatDragon()
    {
        Play firstPlay = createDragon();
        Play secondPlay = createPhoenix();

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_phoenixIsBeatenByDragon()
    {
        Play firstPlay = createPhoenix();
        Play secondPlay = createDragon();

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }
*/
}
