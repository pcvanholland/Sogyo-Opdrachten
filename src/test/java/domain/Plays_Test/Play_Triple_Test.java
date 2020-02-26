package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class Play_Triple_Test extends Play_Test
{
    @Test
    public void test_init()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new PlayingCard(StandardSuit.SWORD, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.JADE, StandardRank.TWO));
        cards.add(new PlayingCard(StandardSuit.PAGODA, StandardRank.TWO));

        new Triple(cards);
    }

    @Test
    public void test_higherBeatsLower()
    {
        Play firstPlay = createTriple(2);
        Play secondPlay = createTriple(3);

        Assert.assertTrue(secondPlay.beats(firstPlay));
    }

    @Test
    // Yes, yes, impossible, I know!
    public void test_equalsNotBeats()
    {
        Play firstPlay = createTriple(2);
        Play secondPlay = createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_lowerNotBeatsHigher()
    {
        Play firstPlay = createTriple(3);
        Play secondPlay = createTriple(2);

        Assert.assertFalse(secondPlay.beats(firstPlay));
    }

    @Test
    public void test_TripleNotBeatSingle()
    {
        Play firstPlay = createTriple(3);
        Play secondPlay = createSingle(2);

        Assert.assertFalse(firstPlay.beats(secondPlay));
    }
}
