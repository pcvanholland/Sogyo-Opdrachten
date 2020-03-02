package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_FOAK_Bomb_Test extends Play_Test_Helper
{
    @Test
    public void test_initFourOfAKind()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));

        new Bomb(cards);
    }

    @Test(expected = InvalidPlayException.class)
    public void test_initFourOfAKindFailsWhenWrong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.THREE));

        new Bomb(cards);
    }

    @Test(expected = InvalidBombException.class)
    public void test_initFourOfAKindFailsWhenTriedWithDifferentType()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.STAR, StandardRank.TWO));

        new Bomb(cards);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createFOAKBomb(2);
        Play secondPlay = createFOAKBomb(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_equalsNotBeats()
    {
        Play firstPlay = createFOAKBomb(2);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createFOAKBomb(3);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsSingle()
    {
        Play firstPlay = createSingle(13);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsPair()
    {
        Play firstPlay = createPair(13);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsStair()
    {
        Play firstPlay = createStair(10, 13);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsTriple()
    {
        Play firstPlay = createTriple(13);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsFullHouse()
    {
        Play firstPlay = createFullHouse(13, 11);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_bombBeatsStraight()
    {
        Play firstPlay = createStraight(2, 13);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_notBeatenByStraight()
    {
        Play firstPlay = createStraight(2, 13);
        Play secondPlay = createFOAKBomb(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
