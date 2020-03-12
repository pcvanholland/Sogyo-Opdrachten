package taipan.domain;

import java.util.ArrayList;

final class FullHouse extends Play
{
    static final int FULL_HOUSE_SIZE = 5;

    private Triple triple;

    /**
     * The default constructor for a FullHouse using a full set of Cards.
     *
     * @param newCards {Card[]} - ArrayList of Cards to make a FullHouse with.
     * @param newOwner {Player} - The Player who played this FullHouse.
     */
    FullHouse(final CardCollection newCards, final Player newOwner) throws
        InvalidPlayException
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
    int getValue()
    {
        return this.triple.getValue();
    }

    /**
     * Gets the Triple provided in the set of Cards.
     *
     * @param cardsToCheck {Card[]} - The Cards to check.
     * @return {Triple} - The Triple filtered from the Cards.
     */
    private Triple getTripleFromCards(final CardCollection cardsToCheck) throws
        InvalidPlayException
    {
        CardCollection result = new CardCollection();
        ArrayList<Integer> ranks = cardsToCheck.getRanks();
        for (Card card : cardsToCheck.getCards())
        {
            if (java.util.Collections.frequency(ranks, card.getValue()) ==
                Triple.TRIPLE_SIZE
            )
            {
                result.add(card);
            }
        }
        return (Triple) result.createPlay(super.getOwner(), Set.TRIPLE);
    }

    /**
     * @param cardsToCheck - An ArrayList of Cards to check.
     * @return {boolean} - Whether the collection of Cards is a FullHouse.
     */
    static boolean isFullHouse(final CardCollection cardsToCheck)
    {
        if (cardsToCheck.size() != FULL_HOUSE_SIZE)
        {
            return false;
        }
        return cardsToCheck.containsTriple() &&
            cardsToCheck.containsPair();
    }
}
