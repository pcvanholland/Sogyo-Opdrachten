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
        // Together they're a Pair.
        if (contextCards.size() == 1 &&
            contextCards.get(0) instanceof PlayingCard
        )
        {
            return getHighestValue(contextCards);
        }
        // A Pair is left, make a Triple.
        else if (Pair.isPair(contextCards))
        {
            return getHighestValue(contextCards);
        }
        // Two of equal rank left, make a FullHouse with the Phoenix as highest.
        else if (contextCards.size() == 4 &&
            PlayHelper.containsOnlyNumberOfEqualRanks(contextCards, 2)
        )
        {
            return getHighestValue(contextCards);
        }
        // A Triple and a Single left, make a FullHouse with the Phoenix paired.
        else if (contextCards.size() == 4 &&
            PlayHelper.containsNumberOfEqualRanks(contextCards, 3)
        )
        {
            return getHighestValue(removeTripleFromCards(contextCards));
        }
        // Enough Cards for a Straight.
        else if (contextCards.size() >= 4 &&
            PlayHelper.containsOnlyNumberOfEqualRanks(contextCards, 1)
        )
        {
            // Already a sequential, just add the Phoenix to the end.
            if (PlayHelper.areCardsSequential(contextCards))
            {
                return getHighestValue(contextCards) + 1;
            }
            return getLocationOfSingleGap(contextCards);
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
            if (!(card instanceof Phoenix))
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

    /**
     * Removes the Triple provided in the set of Cards.
     *
     * @param cards {Card[]} - The Cards to remove the triplicated Cards from.
     * @return {Card[]} - An ArrayList of Cards without the triplicated ones.
     */
    private static ArrayList<Card> removeTripleFromCards(
        final ArrayList<Card> cards
    )
    {
        ArrayList<Card> result = new ArrayList<Card>();
        ArrayList<Integer> ranks = PlayHelper.getRanks(cards);
        for (Card card : cards)
        {
            if (java.util.Collections.frequency(ranks, card.getValue()) != 3)
            {
                result.add(card);
            }
        }
        return result;
    }

    /**
     * A helper function to determine the location of a gap between the Ranks
     * of the specified Cards of exactly one and it occurs exactly once.
     *
     * @param cardsToCheck {Card[]} - The Cards to get the max gap between.
     * @return {int} - The location of the gap. -1 If it is not present or
     *                  present multiple times.
     */
    private static int getLocationOfSingleGap(
        final ArrayList<Card> cardsToCheck
    )
    {
        ArrayList<Integer> gaps = new ArrayList<Integer>();
        ArrayList<Integer> ranksToCheck = PlayHelper.getRanks(cardsToCheck);

        java.util.Collections.sort(ranksToCheck);
        for (int i = 0; i < ranksToCheck.size() - 1; ++i)
        {
            if (ranksToCheck.get(i + 1) - ranksToCheck.get(i) == 2)
            {
                gaps.add(ranksToCheck.get(i) + 1);
            }
        }

        return gaps.size() == 1 ? gaps.get(0) : -1;
    }

    /**
     * Sets the value of the Phoenix in the given
     * set of Cards to the right value.
     *
     * @param cardsToConvert {Card[]} - An ArrayList of Cards to convert.
     * @return {Card[]} - An ArrayList of Cards with the Phoenix corrected.
     */
    protected static void convertPhoenixInSet(
        final ArrayList<Card> cardsToConvert
    )
    {
        for (Card card : cardsToConvert)
        {
            if (card instanceof Phoenix)
            {
                ((Phoenix) card).setValue(determineValueInPlay(cardsToConvert));
            }
        }
    }
}
