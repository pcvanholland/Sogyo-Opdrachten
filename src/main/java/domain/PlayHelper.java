package taipan.domain;

import java.util.ArrayList;

/**
 * This is a helper class for Plays.
 * It is used to determine types of a Play and
 * thus contains some functions to achieve that.
 */
abstract class PlayHelper
{
    /**
     * Determines which types of Play the provided set of Cards can be.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to check.
     * @return {Set[]} - An ArrayList of possible sets to make.
     */
    protected static final ArrayList<Set> determineTypesOfSet(
        final ArrayList<Card> cardsToCheck
    )
    {
        ArrayList<Set> validSets = new ArrayList<Set>();

        if (Single.isSingle(cardsToCheck))
        {
            validSets.add(Set.SINGLE);
        }

        if (Pair.isPair(cardsToCheck))
        {
            validSets.add(Set.PAIR);
        }

        if (Triple.isTriple(cardsToCheck))
        {
            validSets.add(Set.TRIPLE);
        }

        if (Stair.isStair(cardsToCheck))
        {
            validSets.add(Set.STAIR);
        }

        if (Straight.isStraight(cardsToCheck))
        {
            validSets.add(Set.STRAIGHT);
        }

        if (FullHouse.isFullHouse(cardsToCheck))
        {
            validSets.add(Set.FULLHOUSE);
        }

        if (Bomb.isBomb(cardsToCheck))
        {
            validSets.add(Set.BOMB);
        }

        return validSets;
    }

    /**
     * This tests whether the provided Cards are of equal Rank.
     *
     * @param cardsToCheck {Card[]} - The ArrayList of Cards to verify.
     * @return {boolean} - Whether *all* the provided Cards are of equal rank.
     */
    static final boolean areCardsOfEqualRank(
        final ArrayList<Card> cardsToCheck
    )
    {
        Card referenceCard = cardsToCheck.get(0);
        for (Card card : cardsToCheck)
        {
            if (!card.hasEqualRank(referenceCard))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This tests whether the provided Cards are of equal Suit.
     *
     * @param cardsToCheck {Card[]} - The ArrayList of Cards to verify.
     * @return {boolean} - Whether *all* the provided Cards are of equal Suit.
     */
    static final boolean areCardsOfEqualSuit(
        final ArrayList<Card> cardsToCheck
    )
    {
        ISuit firstSuit = cardsToCheck.get(0).getSuit();
        for (Card card : cardsToCheck)
        {
            if (card.getSuit() != firstSuit)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This tests whether the given list of Cards contains any Pair.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to verify.
     * @return {boolean} - Whether the list contains a Pair.
     */
    static final boolean containsPair(
        final ArrayList<Card> cardsToCheck
    )
    {
         return containsNumberOfEqualRanks(cardsToCheck, 2);
    }

    /**
     * This tests whether the given list of Cards contains any Triple.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to verify.
     * @return {boolean} - Whether the list contains a Triple.
     */
    static final boolean containsTriple(
        final ArrayList<Card> cardsToCheck
    )
    {
         return containsNumberOfEqualRanks(cardsToCheck, 3);
    }

    /**
     * This tests whether the given list of Cards contains at least once
     * exactly the specified amount of Cards with the same Rank.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to verify.
     * @param amount {int} - How many of equal Rank may be present.
     *
     * @return {boolean} - Whether the list contains the specified multiplicity.
     */
    private static boolean containsNumberOfEqualRanks(
        final ArrayList<Card> cardsToCheck, final int amount
    )
    {
         ArrayList<Integer> ranks = getRanks(cardsToCheck);
         for (Integer rank : ranks)
         {
             if (java.util.Collections.frequency(ranks, rank) == amount)
             {
                 return true;
             }
         }
         return false;
    }

    /**
     * This tests whether the given list of Cards only contains the
     * specified number of equal Ranks.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to verify.
     * @param amount {int} - How many of equal Rank must be present.
     *
     * @return {boolean} - Whether the list only contains Pairs.
     */
    static final boolean containsOnlyNumberOfEqualRanks(
        final ArrayList<Card> cardsToCheck, final int amount
    )
    {
         ArrayList<Integer> ranks = getRanks(cardsToCheck);
         for (Integer rank : ranks)
         {
             if (java.util.Collections.frequency(ranks, rank) != amount)
             {
                 return false;
             }
         }
         return true;
    }

     /**
      * This tests whether the given list of Cards is sequential.
      *
      * @param cardsToCheck {Card[]} - An ArrayList of Cards to verify.
      * @return {boolean} - Whether the Cards are consecutive.
      */
     static final boolean areCardsSequential(
         final ArrayList<Card> cardsToCheck
     )
     {
         return areRanksSequential(getRanks(cardsToCheck));
     }

    /**
     * This tests whether the given array of Ranks is sequential.
     *
     * @param ranksToCheck {IRank[]} - An ArrayList of Ranks to verify.
     * @return {boolean} - Whether all the Ranks are consecutive.
     */
    private static boolean areRanksSequential(
        final ArrayList<Integer> ranksToCheck
    )
    {
        java.util.Collections.sort(ranksToCheck);
        for (int i = 0; i < ranksToCheck.size() - 1; ++i)
        {
            if (ranksToCheck.get(i + 1) - ranksToCheck.get(i) != 1)
            {
                return false;
            }
        }

        return ranksToCheck.size() > 1;
    }

    /**
     * Get the Ranks of the Cards.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to process.
     * @return {IRank[]} - An ArrayList of the corresponding Ranks.
     */
    protected static ArrayList<Integer> getRanks(
        final ArrayList<Card> cardsToCheck
    )
    {
        ArrayList<Integer> ranks = new ArrayList<Integer>();
        for (Card card : cardsToCheck)
        {
            ranks.add(card.getValue());
        }
        return ranks;
    }

    /**
     * Creates a proper Play of the given Cards.
     *
     * @param cards {Card[]} - An ArrayList of Cards to convert.
     * @param set {Set} - The type of Play to use.
     *
     * @return {Play} - The Play the Cards have become.
     */
    protected static final Play createPlay(
        final ArrayList<Card> cards,
        final Set set
    )
    {
        if (set.equals(Set.SINGLE))
        {
            return new Single(cards);
        }
        if (set.equals(Set.PAIR))
        {
            return new Pair(cards);
        }
        if (set.equals(Set.TRIPLE))
        {
            return new Triple(cards);
        }
        if (set.equals(Set.STAIR))
        {
            return new Stair(cards);
        }
        if (set.equals(Set.FULLHOUSE))
        {
            return new FullHouse(cards);
        }
        if (set.equals(Set.STRAIGHT))
        {
            return new Straight(cards);
        }
        if (set.equals(Set.BOMB))
        {
            return new Bomb(cards);
        }
        // We can't arrive here.
        throw new InvalidPlayException();
    }
}
