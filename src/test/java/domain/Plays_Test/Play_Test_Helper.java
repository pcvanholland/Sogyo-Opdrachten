package taipan.domain;

import java.util.ArrayList;

public abstract class Play_Test_Helper
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
            StandardSuit.values()[rng.nextInt(3)],
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
        cards.add(new PlayingCard(StandardSuit.SWORD,
            StandardRank.values()[current++]
        ));
        cards.add(new PlayingCard(StandardSuit.PAGODA,
            StandardRank.values()[current++]
        ));
        current += 2;

        while (current < end + 1)
        {
            cards.add(createRandomCard(current++));
        }
        java.util.Collections.shuffle(cards);
        return new Straight(cards);
    }

    /**
     * Creates a FourOfAKind-Bomb Play.
     *
     * @param value {int} - The value the Cards ought to have.
     * @return {Bomb} - The Play created.
     */
    final static Bomb createFOAKBomb(final int value)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (StandardSuit suit : StandardSuit.values())
        {
            cards.add(new PlayingCard(suit, StandardRank.values()[value - 2]));
        }
        java.util.Collections.shuffle(cards);
        return new Bomb(cards);
    }

    /**
     * Creates a Straight-Bomb Play with a random Suit.
     *
     * @param start {int} - The lowest value of the Straight.
     * @param end {int} - The highest value of the Straight.
     *
     * @return {Bomb} - The Play created.
     */
    final static Bomb createStraightBomb(final int start, final int end)
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        StandardSuit suit = StandardSuit.values()[rng.nextInt(3)];
        int current = start - 2;
        while (current < end - 1)
        {
            cards.add(new PlayingCard(suit, StandardRank.values()[current++]));
        }
        java.util.Collections.shuffle(cards);
        return new Bomb(cards);
    }

    /**
     * Creates a Single Play with a Dragon.
     *
     * @return {Single} - The Play created.
     */
    final static Single createDragon()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.DRAGON));
        return new Single(cards);
    }

    /**
     * Creates a Single Play with a Mahjong.
     *
     * @return {Single} - The Play created.
     */
    final static Single createMahjong()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.MAHJONG));
        return new Single(cards);
    }

    /**
     * Creates a Single Play with a Phoenix.
     *
     * @return {Single} - The Play created.
     */
    final static Single createPhoenix()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new SpecialCard(SpecialRank.PHOENIX));
        return new Single(cards);
    }
}
