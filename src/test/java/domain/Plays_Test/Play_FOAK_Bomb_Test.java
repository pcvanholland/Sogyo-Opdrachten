package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_FOAK_Bomb_Test extends Play_Test
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
