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
        if (Pair.isPair(contextCards))
        {
            return getHighestValue(contextCards);
        }
        // Two of equal rank left, make a FullHouse with the Phoenix as highest.
        if (contextCards.size() == 4 &&
            PlayHelper.containsOnlyNumberOfEqualRanks(contextCards, 2)
        )
        {
            return getHighestValue(contextCards);
        }
        // A Triple and a Single left, make a FullHouse with the Phoenix paired.
        if (contextCards.size() == 4 &&
            PlayHelper.containsNumberOfEqualRanks(contextCards, 3)
        )
        {
            return getHighestValue(removeTripleFromCards(contextCards));
        }
        // Enough Cards for a Straight.
        if (contextCards.size() >= 4 &&
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
        // If there are only pairs (minus one) left, and we can match the
        // single we might be a Stair.
        if (contextCards.size() % 2 != 0)
        {
            if (containsOnlyOneSingle(contextCards) &&
                restIsPairs(contextCards)
            )
            {
                return getSingleValue(contextCards);
            }
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
        return removeSetsFromCards(cards, 3);
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
     * Whether the given set of Cards only contains Pairs except for one Single.
     *
     * @param cardsToCheck {Card[]} - The Cards to check.
     * @return {boolean} - Whether the given set of Cards only contains Pairs
     *                  except for the one Single.
     */
    private static boolean restIsPairs(final ArrayList<Card> cardsToCheck)
    {
        ArrayList<Card> cardsWithoutSingle =
            removeSinglesFromCards(cardsToCheck);
        return PlayHelper.containsOnlyNumberOfEqualRanks(cardsWithoutSingle, 2);
    }

    /**
     * Whether the given list of Cards contains only one Single.
     *
     * @param cardsToCheck {Card[]} - The Cards to check.
     * @return {boolean} - Whether there is exactly one Single in the list.
     */
    private static boolean containsOnlyOneSingle(
        final ArrayList<Card> cardsToCheck
    )
    {
        boolean single = false;
        ArrayList<Integer> ranks = PlayHelper.getRanks(cardsToCheck);
        for (int i = 0; i < ranks.size(); ++i)
        {
            if (java.util.Collections.frequency(ranks, ranks.get(i)) == 1)
            {
                if (single)
                {
                    return false;
                }
                single = true;
            }
        }
        return single;
    }

    /**
     * Finds the value of the Single present in the given set of Cards.
     *
     * @param cardsToCheck {Card[]} - The Cards to check.
     * @return {int} - The value of the Single.
     */
    private static int getSingleValue(
        final ArrayList<Card> cardsToCheck
    )
    {
        ArrayList<Integer> ranks = PlayHelper.getRanks(cardsToCheck);
        for (int i = 0; i < ranks.size(); ++i)
        {
            if (java.util.Collections.frequency(ranks, ranks.get(i)) == 1)
            {
                return ranks.get(i);
            }
        }
        return -1;
    }

    /**
     * Removes the Singles provided in the set of Cards.
     *
     * @param cards {Card[]} - The Cards to remove the singular Cards from.
     * @return {Card[]} - An ArrayList of Cards without the single ones.
     */
    private static ArrayList<Card> removeSinglesFromCards(
        final ArrayList<Card> cards
    )
    {
        return removeSetsFromCards(cards, 1);
    }

    /**
     * Removes the Cards occuring the specified amount of times
     * provided in the set of Cards.
     *
     * @param cards {Card[]} - The Cards to remove the triplicated Cards from.
     * @param amount {Card[]} - The multiplicity the Cards may not occur.
     *
     * @return {Card[]} - An ArrayList of Cards without the multiplicated ones.
     */
    private static ArrayList<Card> removeSetsFromCards(
        final ArrayList<Card> cards, final int amount
    )
    {
        ArrayList<Card> result = new ArrayList<Card>();
        ArrayList<Integer> ranks = PlayHelper.getRanks(cards);
        for (Card card : cards)
        {
            if (java.util.Collections.frequency(
                    ranks, card.getValue()
                ) != amount
            )
            {
                result.add(card);
            }
        }
        return result;
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
