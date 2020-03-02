package taipan.domain;

import java.util.ArrayList;

/**
 * This class represents a Card dealer.
 * Players can ask them for a hand of Cards.
 */
class Dealer
{
    // Sum *MUST* be 14.
    private static final int FIRST_HAND_SIZE = 6;
    private static final int SECOND_HAND_SIZE = 8;

    private ArrayList<Card> cards;

    /**
     * Constructs a dealer.
     */
    Dealer()
    {
        createAndShuffleCards(new java.util.Random());
    }

    /**
     * Constructs a dealer with a predefined seed.
     */
    Dealer(final int seed)
    {
        createAndShuffleCards(new java.util.Random(seed));
    }

    /**
     * Creates and shuffles a set of cards.
     */
    private void createAndShuffleCards(final java.util.Random rng)
    {
        createCards();
        java.util.Collections.shuffle(this.cards, rng);
    }

    /**
     * Creates a set of cards.
     */
    private void createCards()
    {
        this.cards = new ArrayList<Card>();
        addStandardCards();
        addSpecialCards();
    }

    /**
     * Adds the standard cards to the card array.
     */
    private void addStandardCards()
    {
        for (StandardSuit suit : StandardSuit.values())
        {
            for (StandardRank rank : StandardRank.values())
            {
                this.cards.add(new PlayingCard(suit, rank));
            }
        }
    }

    /**
     * Adds the standard cards to the card array.
     */
    private void addSpecialCards()
    {
        for (SpecialRank rank : SpecialRank.values())
        {
            this.cards.add(new SpecialCard(rank));
        }
    }

    /**
     * This lets a Player draw the first six cards.
     *
     * @return {Card[]} - An ArrayList of cards taken from the stack.
     */
    protected final ArrayList<Card> drawFirstHand()
    {
        return drawCards(this.FIRST_HAND_SIZE);
    }

    /**
     * This lets a Player draw the next eight cards.
     *
     * @return {Card[]} - An ArrayList of cards taken from the stack.
     */
    protected final ArrayList<Card> drawSecondHand()
    {
        return drawCards(this.SECOND_HAND_SIZE);
    }

    /**
     * Draws a portion of the cards.
     *
     * @param numberOfCards {int} - The number of cards to draw.
     * @return {Card[]} - An ArrayList of cards taken from the stack.
     */
    private ArrayList<Card> drawCards(final int numberOfCards) throws
        DealerOutOfCardsException
    {
        if (this.cards.size() < numberOfCards)
        {
            throw new DealerOutOfCardsException();
        }
        ArrayList<Card> cardsDrawn = new ArrayList<Card>();
        while (cardsDrawn.size() < numberOfCards)
        {
            cardsDrawn.add(this.cards.remove(0));
        }
        return cardsDrawn;
    }
}
