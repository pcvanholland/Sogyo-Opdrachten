package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlayHelper_Test extends Play_Test_Helper
{

/*
    @Test
    public void test_() throws Exception
    {
        for (int i = 0; i < 100000000; ++i)
        {
            Table playingTable = new Table();
            Player player = new Player(playingTable, i);
            player.drawCards();
            player.drawCards();

            CardCollection cards = new CardCollection();
            boolean mj = false;
            boolean phnx = false;
            for (Card card : player.getCards())
            {
                if (card.getRank() == SpecialRank.MAHJONG)
                {
                    mj = true;
                }
                if (card.getRank() == SpecialRank.PHOENIX)
                {
                    phnx = true;
                }
                cards.add(card);
            }
            if (!mj || !phnx)
            {
                continue;
            }
            if (cards.determineTypesOfSet().contains(Set.STRAIGHT))
            {
                Assert.assertNotEquals(i, i);
                break;
            }
        }
    }
//*/
    @Test
    public void test_single() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.SINGLE)
                instanceof Single
        );
    }

    @Test
    public void test_pair() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.PAIR)
                instanceof Pair
        );
    }

    @Test
    public void test_triple() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.TRIPLE)
                instanceof Triple
        );
    }

    @Test
    public void test_stair() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(3));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.STAIR)
                instanceof Stair
        );
    }

    @Test
    public void test_straight() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(3));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(5));
        cards.add(createRandomCard(6));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.STRAIGHT)
                instanceof Straight
        );
    }

    @Test
    public void test_fullHouse() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));
        cards.add(createRandomCard(4));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.FULLHOUSE)
                instanceof FullHouse
        );
    }

    @Test
    public void test_FOAKBomb() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));
        cards.add(createRandomCard(2));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.BOMB)
                instanceof Bomb
        );
    }

    @Test
    public void test_straightBombNotBomb() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.STRAIGHT)
                instanceof Straight
        );
    }

    @Test
    public void test_straightBomb() throws TaiPanException
    {
        CardCollection cards = new CardCollection();
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.THREE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FOUR));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.FIVE));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.SIX));

        Assert.assertTrue(
            cards.createPlay(TEST_PLAYER, Set.BOMB)
                instanceof Bomb
        );
    }
}
