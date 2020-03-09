package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Straight_Bomb_Test extends Play_Test_Helper
{
    @Test
    public void test_playInValidityTooShortArray()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playValidityStraightBomb()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STRAIGHT);
        result.add(Set.BOMB);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInValidityBrokenStraightBomb()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SEVEN));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInValidityStraightBombWithExtra()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.EIGHT));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInValidityStraightWithPair()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_playInValidityStraightWithOnlyPairs()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.SIX));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.FOUR));

        ArrayList<Set> result = new ArrayList<Set>();
        result.add(Set.STAIR);

        Assert.assertEquals(result, cards.determineTypesOfSet());
    }

    @Test
    public void test_initStraightBomb()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        new Bomb(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFailsWhenWrong()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));

        new Bomb(cards, TEST_PLAYER);
    }

    @Test(expected = InvalidBombException.class)
    public void test_initFailsWhenTriedWithDifferentType()
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        new Bomb(cards, TEST_PLAYER);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createStraightBomb(2, 6);
        Play secondPlay = createStraightBomb(3, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createStraightBomb(2, 6);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createStraightBomb(3, 7);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsSingle()
    {
        Play firstPlay = createSingle(13);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsPair()
    {
        Play firstPlay = createPair(13);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsStair()
    {
        Play firstPlay = createStair(10, 13);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsTriple()
    {
        Play firstPlay = createTriple(13);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsFullHouse()
    {
        Play firstPlay = createFullHouse(13, 11);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsStraight()
    {
        Play firstPlay = createStraight(2, 13);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_notBeatenByNormalStraight()
    {
        Play firstPlay = createStraight(2, 13);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }

    @Test
    public void test_straightBombBeatsFOAKBomb()
    {
        Play firstPlay = createFOAKBomb(13);
        Play secondPlay = createStraightBomb(2, 6);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_straightBombNotBeatenByFOAKBomb()
    {
        Play firstPlay = createStraightBomb(2, 6);
        Play secondPlay = createFOAKBomb(13);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_largerStraightBombBeatsSmallerStraightBomb()
    {
        Play firstPlay = createStraightBomb(9, 13);
        Play secondPlay = createStraightBomb(2, 7);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_smallerStraightBombCannotBeatLargerStraightBomb()
    {
        Play firstPlay = createStraightBomb(2, 7);
        Play secondPlay = createStraightBomb(9, 13);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }
}
