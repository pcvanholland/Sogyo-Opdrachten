package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Phoenix_Test extends Play_Test_Helper
{
    @Test
    public void test_init()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        new Single(cards, TEST_PLAYER);
    }

    @Test
    public void test_playValiditySingle()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.SINGLE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfPair()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.PAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfDifferentPair()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.PAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCantPairedWithMahJong()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCantBePairedWithDog()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.DOG));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCantBePairedWithDragon()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.DRAGON));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfPairOrderDoesntMatter()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.PAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfTriple()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.TRIPLE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfTripleOrderDoesntMatter()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.TRIPLE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCantBePartOfInvalidTriple()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCantBePartOfInvalidTripleWithSpecialCard()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.MAHJONG));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfFullHouse()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfFullHouseOrderDoesntMatter()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfStair()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfStairOrderDoesntMatter()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfStraight()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCanBePartOfStraightOrderDoesntMatter()
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }
/*
    @Test
    public void test_phoenixCantBePartOfLargestStraight()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            cards.add(createRandomCard(i));
        }

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCantBePartOfLargestStraightOrderDoesntMatter()
    {
        CardCollection cards = new CardCollection();
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            cards.add(createRandomCard(i));
        }
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }
*/
    @Test
    public void test_phoenixCantBomb()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_phoenixCantBombStraight()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }
/*
    @Test
    public void test_playValidityFullHouseOfFiveEquals()
    {
        CardCollection cards = new CardCollection();
        cards.add(SpecialCard.createSpecialCard(SpecialRank.PHOENIX));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.FULLHOUSE);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }
*//*
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
*/
    @Test
    public void test_phoenixIsBeatenByAllStandardSingles()
    {
        Play firstPlay = createPhoenix();
        Play secondPlay;
        for (int i = 2; i < StandardRank.values().length + 2; ++i)
        {
            secondPlay = createSingle(i);
            Assert.assertTrue(secondPlay.beats(firstPlay));
        }
    }
/*
    @Test
    public void test_phoenixCantBeBeatenByAceOnAce()
    {
        Play firstPlay = createSingle(StandardRank.values().length + 1);
        Play secondPlay = createPhoenix();
        Trick trick = new Trick(firstPlay);
        trick.play(secondPlay);

        Assert.assertFalse(firstPlay.beats(trick.getLastPlay()));
    }
*/
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
}
