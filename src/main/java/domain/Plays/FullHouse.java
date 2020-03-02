package taipan.domain;

import java.util.ArrayList;

class FullHouse extends Play
{
    private Triple triple;

    /**
     * The default constructor for a FullHouse using a full set of Cards.
     *
     * @param newCards {Card[]} - An ArrayList of Cards to make a FullHouse with.
     */
    FullHouse(final ArrayList<Card> newCards)
    {
        super(newCards);
        if (!isFullHouse(newCards))
        {
            throw new InvalidFullHouseException();
        }
        this.triple = getTripleFromCards(newCards);
    }

    /**
     * The default constructor for a FullHouse.
     *
     * @param newTriple {Triple} - The Triple that is part of this FullHouse.
     * @param newPair {Pair} - The Pair that is part of this FullHouse.
     */
    FullHouse(final Triple newTriple, final Pair newPair) throws
        InvalidPlayException
    {
        super(getAllCards(newTriple, newPair));
        if (!isFullHouse(getAllCards(newTriple, newPair)))
        {
            throw new InvalidFullHouseException();
        }
        this.triple = newTriple;
    }

    /**
     * The inverted constructor for a FullHouse.
     * For user convenience,,,
     *
     * @param newPair {Pair} - The Pair that is part of this FullHouse.
     * @param newTriple {Triple} - The Triple that is part of this FullHouse.
     */
    FullHouse(final Pair newPair, final Triple newTriple) throws
        InvalidPlayException
    {
        this(newTriple, newPair);
    }

    /**
     * Calculates the value of this Play. I.e. the Rank of the Triple.
     *
     * @return {int} - The value of this Play.
     */
    @Override
    protected int getValue()
    {
        return this.triple.getValue();
    }

    /**
     * Retreives all Cards by querying both the Pair and the Triple.
     *
     * @param newTriple {Triple} - The Triple to check.
     * @param newPair {Pair} - The Pair to check.
     *
     * @return {Card[]} - An ArrayList of the Cards.
     */
    private static ArrayList<Card> getAllCards(
        final Triple newTriple, final Pair newPair
    )
    {
        ArrayList<Card> result = new ArrayList<Card>();
        result.addAll(newTriple.getCards());
        result.addAll(newPair.getCards());
        return result;
    }

    /**
     * Gets the Triple provided in the set of Cards.
     *
     * @param cardsToCheck {Card[]} - The Cards to check.
     * @return {Triple} - The Triple filtered from the Cards.
     */
    private static Triple getTripleFromCards(final ArrayList<Card> cardsToCheck)
    {
        ArrayList<Card> result = new ArrayList<Card>();
        for (Card card : cardsToCheck)
        {
            if (java.util.Collections.frequency(
                    PlayHelper.getRanks(cardsToCheck), card.getValue()
                ) == 3)
            {
                result.add(card);
            }
        }
        return new Triple(result);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether the collection of Cards is a FullHouse.
     */
    protected static boolean isFullHouse(final ArrayList<Card> cardsToCheck)
    {
        if (cardsToCheck.size() != 5)
        {
            return false;
        }
        return PlayHelper.containsTriple(cardsToCheck) &&
            PlayHelper.containsPair(cardsToCheck);
    }
}
