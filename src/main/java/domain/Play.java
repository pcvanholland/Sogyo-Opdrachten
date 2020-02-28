package taipan.domain;

import java.util.ArrayList;

abstract class Play
{
    private ArrayList<Card> cards;

    /**
     * Constructor for a Play (a set of Cards played).
     *
     * @param newCards {Card[]} - An ArrayList of Cards bundled in this Play.
     */
    Play(final ArrayList<Card> newCards)
    {
        this.cards = newCards;
    }

    /**
     * Whether this Play is higher in value than the provided one.
     *
     * @param playToBeat {Play} - The Play to beat.
     * @return {boolean} - Whether this Play beats the provided one.
     */
    protected boolean beats(final Play playToBeat)
    {
        return this.isSameSetAs(playToBeat) &&
            this.getValue() > playToBeat.getValue();
    }

    /**
     * Calculates the value of this Play. which is usually the largest value
     * present, except for a FullHouse.
     *
     * @return {int} - The value of this Play.
     */
     protected int getValue()
     {
         int highest = -1;
         for (Card card : this.getCards())
         {
             highest = Math.max(card.getValue(), highest);
         }
         return highest;
     }

    /**
     * Gets the cards belonging to this Play.
     *
     * @return {Card[]} - An ArrayList of Cards in this Play.
     */
    final ArrayList<Card> getCards()
    {
        return this.cards;
    }

    /**
     * Tests whether this Play is equal to the Play to check.
     * I.e. same class and same length.
     *
     * @param play {Play} - The Play to check against.
     * @return {boolean} - Whether the two Plays are equal.
     */
    private boolean isSameSetAs(final Play play)
    {
        return this.getClass() == play.getClass() &&
            this.getLength() == play.getLength();
    }

    /**
     * @return {int} - The size of the array of Cards.
     */
    final int getLength()
    {
        return this.getCards().size();
    }

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
    private static ArrayList<Integer> getRanks(
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
}
