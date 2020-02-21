package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Straight_Bomb_Test extends Play_Test
{
    @Test
    public void test_initFourOfAKind()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(Suit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.TWO));

        new Bomb(cards);
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
