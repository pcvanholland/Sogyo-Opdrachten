package taipan.domain;

import java.util.ArrayList;

public abstract class Play_Test
{
    private final static java.util.Random rng = new java.util.Random(0);

    /**
     * Creates a Single Card with a random Suit.
     *
     * @param value {int} - The value the card ought to have.
     * @return {Card} - The Card created.
     */
    final static Card createRandomCard(final int value)
    {
        return new PlayingCard(
            Suit.values()[rng.nextInt(3)],
            StandardRank.values()[value - 2]
        );
    }

    /**
     * Creates a Single Play with a random Suit.
     *
     * @param value {int} - The value the card ought to have.
     * @return {Single} - The Play created.
     */
    final static Single createSingle(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        return new Single(cards);
    }

    /**
     * Creates a Pair Play with random Suits.
     *
     * @param value {int} - The value the Cards ought to have.
     * @return {Pair} - The Play created.
     */
    final static Pair createPair(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        cards.add(createRandomCard(value));
        return new Pair(cards);
    }

    /**
     * Creates a Stair Play with random Suits.
     *
     * @param start {int} - The lowest value of the Stair.
     * @param end {int} - The highest value of the Stair.
     *
     * @return {Stair} - The Play created.
     */
    final static Stair createStair(final int start, final int end)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int current = start; current < end + 1; ++current)
        {
            cards.add(createRandomCard(current));
            cards.add(createRandomCard(current));
        }
        java.util.Collections.shuffle(cards);
        return new Stair(cards);
    }

    /**
     * Creates a Triple Play with random Suits.
     *
     * @param value {int} - The value the Cards ought to have.
     * @return {Triple} - The Play created.
     */
    final static Triple createTriple(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(createRandomCard(value));
        cards.add(createRandomCard(value));
        cards.add(createRandomCard(value));
        return new Triple(cards);
    }

    /**
     * Creates a FullHouse Play with random Suits.
     *
     * @param tripleValue {int} - The value the Triple ought to have.*
     * @param pairValue {int} - The value the Pair ought to have.
     *
     * @return {FullHouse} - The Play created.
     */
    final static FullHouse createFullHouse(
        final int tripleValue, final int pairValue
    )
    {
        return new FullHouse(createTriple(tripleValue), createPair(pairValue));
    }

    /**
     * Creates a straight with the specified start and end
     * values with semi-random suits.
     *
     * @param start {int} - The lowest value of the Straight.
     * @param end {int} - The highest value of the Straight.
     *
     * @return {Straight} - The resulting Straight.
     */
    final static Straight createStraight(final int start, final int end)
    {
        ArrayList<Card> cards = new ArrayList<Card>();

        // First two cards ought to be different to prevent an accidental bomb.
        int current = start - 2;
        cards.add(new PlayingCard(Suit.SWORD, StandardRank.values()[current++]));
        cards.add(new PlayingCard(Suit.PAGODA, StandardRank.values()[current++]));
        current += 2;

        while (current < end + 1)
        {
            cards.add(createRandomCard(current++));
        }
        java.util.Collections.shuffle(cards);
        return new Straight(cards);
    }
}
