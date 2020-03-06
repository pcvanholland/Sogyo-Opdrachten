package taipan.domain;

import java.util.ArrayList;

class FullHouse extends Play
{
    private Triple triple;

    /**
     * The default constructor for a FullHouse using a full set of Cards.
     *
     * @param newCards {Card[]} - ArrayList of Cards to make a FullHouse with.
     * @param newOwner {Player} - The Player who played this FullHouse.
     */
    FullHouse(final ArrayList<Card> newCards, final Player newOwner)
    {
        super(newCards, newOwner);
        if (!isFullHouse(newCards))
        {
            throw new InvalidFullHouseException();
        }
        this.triple = getTripleFromCards(newCards);
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
     * Gets the Triple provided in the set of Cards.
     *
     * @param cardsToCheck {Card[]} - The Cards to check.
     * @return {Triple} - The Triple filtered from the Cards.
     */
    private Triple getTripleFromCards(final ArrayList<Card> cardsToCheck)
    {
        ArrayList<Card> result = new ArrayList<Card>();
        ArrayList<Integer> ranks = PlayHelper.getRanks(cardsToCheck);
        for (Card card : cardsToCheck)
        {
            if (java.util.Collections.frequency(ranks, card.getValue()) == 3)
            {
                result.add(card);
            }
        }
        return (Triple) PlayHelper.createPlay(
            result, super.getOwner(), Set.TRIPLE
        );
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
