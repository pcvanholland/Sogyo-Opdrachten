package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlayHelper_Test extends Play_Test_Helper
{
    @Test
    public void test_single()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.SINGLE) instanceof Single
        );
    }

    @Test
    public void test_pair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.PAIR) instanceof Pair
        );
    }

    @Test
    public void test_triple()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.TRIPLE) instanceof Triple
        );
    }

    @Test
    public void test_stair()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.STAIR) instanceof Stair
        );
    }

    @Test
    public void test_straight()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.STRAIGHT) instanceof Straight
        );
    }

    @Test
    public void test_fullHouse()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.FULLHOUSE) instanceof FullHouse
        );
    }

    @Test
    public void test_FOAKBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.BOMB) instanceof Bomb
        );
    }

    @Test
    public void test_straightBombNotBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.STRAIGHT) instanceof Straight
        );
    }

    @Test
    public void test_straightBomb()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        Assert.assertTrue(
            PlayHelper.createPlay(cards, Set.BOMB) instanceof Bomb
        );
    }
}
