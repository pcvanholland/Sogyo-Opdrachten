package taipan.domain;

import java.util.ArrayList;

abstract class Play
{
    private ArrayList<Card> cards;

    /**
     * Constructor for a play (a set of cards played).
     *
     * @param newCards {Card[]} - An ArrayList of cards bundled in this play.
     */
    Play(final ArrayList<Card> newCards)
    {
        this.cards = newCards;
    }

    /**
     * Whether this Play is higher in value than the provided one.
     *
     * @param playToBeat {Play} - The play to beat.
     * @return {boolean} - Whether this play beats the provided one.
     */
    protected boolean beats(final Play playToBeat)
    {
        return this.isSameSetAs(playToBeat) &&
            this.getValue() > playToBeat.getValue();
    }

    /**
     * Calculates the value of this Play. which is usually the largest value
     * present, except for a Bomb or FullHouse.
     *
     * @return {int} - The value of this Play.
     */
     protected int getValue()
     {
         int highest = -1;
         for (Card card : this.getCards())
         {
             highest = Math.max(card.getRank().getValue(), highest);
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
     * @param play {Play} - The play to check against.
     * @return {boolean} - Whether the two plays are equal.
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
     * Determines which types of Play the provided set of cards can be.
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
     * This tests whether the provided cards are of equal rank.
     *
     * @param cardsToCheck {Card[]} - The ArrayList of cards to verify.
     * @return {boolean} - Whether *all* the provided cards are of equal rank.
     */
    final static boolean areCardsOfEqualRank(
        final ArrayList<Card> cardsToCheck
    )
    {
        IRank firstRank = cardsToCheck.get(0).getRank();
        for (Card card : cardsToCheck)
        {
            if (card.getRank() != firstRank)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This tests whether the provided cards are of equal Suit.
     *
     * @param cardsToCheck {Card[]} - The ArrayList of cards to verify.
     * @return {boolean} - Whether *all* the provided cards are of equal Suit.
     */
    final static boolean areCardsOfEqualSuit(
        final ArrayList<Card> cardsToCheck
    )
    {
        // for (Card card : cardsToCheck)
        // {
        //     if (card instanceof SpecialCard)
        //     {
        //         return false;
        //     }
        // }
        Suit firstSuit = ((PlayingCard) cardsToCheck.get(0)).getSuit();
        for (Card card : cardsToCheck)
        {
            if (((PlayingCard) card).getSuit() != firstSuit)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This tests whether the given list of cards contains any Pair.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of cards to verify.
     * @return {boolean} - Whether the list contains a Pair.
     */
    final static boolean containsPair(
        final ArrayList<Card> cardsToCheck
    )
    {
         return containsNumberOfEqualRanks(cardsToCheck, 2);
    }

    /**
     * This tests whether the given list of cards contains any Pair.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of cards to verify.
     * @return {boolean} - Whether the list contains a Pair.
     */
    final static boolean containsTriple(
        final ArrayList<Card> cardsToCheck
    )
    {
         return containsNumberOfEqualRanks(cardsToCheck, 3);
    }

    /**
     * This tests whether the given list of cards contains any Pair.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of cards to verify.
     * @return {boolean} - Whether the list contains a Pair.
     */
    private static boolean containsNumberOfEqualRanks(
        final ArrayList<Card> cardsToCheck, final int amount
    )
    {
         ArrayList<IRank> ranks = getRanks(cardsToCheck);
         for (IRank rank : ranks)
         {
             if (java.util.Collections.frequency(ranks, rank) == amount)
             {
                 return true;
             }
         }
         return false;
    }

    /**
     * This tests whether the given list of cards only contains pairs.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of cards to verify.
     * @return {boolean} - Whether the list only contains pairs.
     */
    final static boolean containsOnlyNumberOfEqualRanks(
        final ArrayList<Card> cardsToCheck, final int amount
    )
    {
         ArrayList<IRank> ranks = getRanks(cardsToCheck);
         for (IRank rank : ranks)
         {
             if (java.util.Collections.frequency(ranks, rank) != amount)
             {
                 return false;
             }
         }
         return true;
    }

     /**
      * This tests whether the given list of cards is sequential.
      *
      * @param cardsToCheck {Card[]} - An ArrayList of cards to verify.
      * @return {boolean} - Whether the cards are consecutive.
      */
     final static boolean areCardsSequential(
         final ArrayList<Card> cardsToCheck
     )
     {
         return containsOnlyNumberOfEqualRanks(cardsToCheck, 1) &&
            areRanksSequential(getRanks(cardsToCheck));
     }

    /**
     * This tests whether the given array of ranks is sequential.
     *
     * @param ranksToCheck {IRank[]} - An ArrayList of ranks to verify.
     * @return {boolean} - Whether all the ranks are consecutive.
     */
    private static boolean areRanksSequential(
        final ArrayList<IRank> ranksToCheck
    )
    {
        for (IRank firstRank : ranksToCheck)
        {
            // Everything larger than 1 is okay here.
            int delta = 2;
            for (IRank secondRank : ranksToCheck)
            {
                if (secondRank == firstRank)
                {
                    continue;
                }

                int firstValue = ((StandardRank) firstRank).getValue();
                int secondValue = ((StandardRank) secondRank).getValue();

                delta = Math.min(Math.abs(firstValue - secondValue), delta);
            }
            if (delta > 1)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Get the ranks of the Cards.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of Cards to process.
     * @return {IRank[]} - An ArrayList of the corresponding Ranks.
     */
    private static ArrayList<IRank> getRanks(
        final ArrayList<Card> cardsToCheck
    )
    {
        ArrayList<IRank> ranks = new ArrayList<IRank>();
        for (Card card : cardsToCheck)
        {
            ranks.add(card.getRank());
        }
        return ranks;
    }
}
