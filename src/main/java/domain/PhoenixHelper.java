package taipan.domain;

import java.util.ArrayList;

abstract class PhoenixHelper
{
    /**
     * Determines the value of a Phoenix given the context Cards.
     *
     * @param cardsToCheck {Card[]} - The context in which the Phoenix exists.
     * @return {int} - The value of the Phoenix.
     */
    protected static int determineValueInPlay(
        final ArrayList<Card> cardsToCheck
    )
    {
        ArrayList<Card> contextCards = removePhoenix(cardsToCheck);
        if (contextCards.size() == 1 &&
            contextCards.get(0) instanceof PlayingCard
        )
        {
            return contextCards.get(0).getValue();
        }
        else if (contextCards.size() == 2 &&
            PlayHelper.containsPair(contextCards)
        )
        {
            return contextCards.get(0).getValue();
        }
        else if (contextCards.size() == 4 &&
            PlayHelper.containsOnlyNumberOfEqualRanks(contextCards, 2)
        )
        {
            return getHighestValue(contextCards);
        }
        return -1;
    }

    /**
     * Helper function to remove the Phoenix from a set of Cards.
     *
     * @param cards {Card[]} - The context in which the Phoenix exists.
     * @return {Card[]} - The context whithout the Phoenix.
     */
    private static ArrayList<Card> removePhoenix(final ArrayList<Card> cards)
    {
        ArrayList<Card> result = new ArrayList<Card>();
        for (Card card : cards)
        {
            if (card.getRank() != SpecialRank.PHOENIX)
            {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * Helper function to get the highest value of a given set of Cards.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to check.
     * @return {int} - The highest value present in this set.
     */
    private static int getHighestValue(final ArrayList<Card> cardsToCheck)
    {
        int result = -1;
        for (Card card : cardsToCheck)
        {
            result = Math.max(card.getValue(), result);
        }
        return result;
    }
}
