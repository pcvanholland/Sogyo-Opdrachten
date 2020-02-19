package taipan.domain;

import java.util.ArrayList;

class Play
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
     * This tests whether a given play is valid.
     * Regardless of the cards in a Trick.
     *
     * @param cardsToVerify {Card[]} - The ArrayList of cards to verify.
     * @return {boolean} - The validity of the tested play.
     */
    protected static boolean isValidPlay(final ArrayList<Card> cardsToVerify)
    {
        return areCardsOfEqualRank(cardsToVerify) ||
            areCardsASetOfPairs(cardsToVerify);
    }

    /**
     * This tests whether the provided cards are of equal rank.
     *
     * @param cardsToCheck {Card[]} - The ArrayList of cards to verify.
     * @return {boolean} - Whether *all* the provided cards are of equal rank.
     */
    private static boolean areCardsOfEqualRank(
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
     * This tests whether the cards provided is a set of sets.
     *
     * @param cardsToCheck {Card[]} - The ArrayList of cards to verify.
     * @return {boolean} - Whether the provided cards are a set of sets.
     */
    private static boolean areCardsASetOfPairs(
        final ArrayList<Card> cardsToCheck
    )
    {
        // An odd-sized array is never a valid set.
        if (cardsToCheck.size() % 2 != 0)
        {
            return false;
        }

        if (!areCardsOnlyPairs(cardsToCheck))
        {
            return false;
        }

        if (!arePairsOfCardsSequential(cardsToCheck))
        {
            return false;
        }
        return true;
    }

    /**
     * This tests whether the given list of cards only contains pairs.
     *
     * @param cardsToCheck {Card[]} - An ArrayList of cards to verify.
     * @return {boolean} - Whether the list only contains pairs.
     */
     private static boolean areCardsOnlyPairs(
         final ArrayList<Card> cardsToCheck
     )
     {
         ArrayList<IRank> ranks = new ArrayList<IRank>();
         for (Card card : cardsToCheck)
         {
             ranks.add(card.getRank());
         }

         for (IRank rank : ranks)
         {
             if (java.util.Collections.frequency(ranks, rank) != 2)
             {
                 return false;
             }
         }
         return true;
     }

     /**
      * This tests whether the given list of pairs is sequential.
      *
      * @param cardsToCheck {Card[]} - An ArrayList of pairs to verify.
      * @return {boolean} - Whether the pairs are consecutive.
      */
     private static boolean arePairsOfCardsSequential(
         final ArrayList<Card> cardsToCheck
     )
     {
         java.util.HashSet<IRank> uniqueRanks = new java.util.HashSet<IRank>();
         for (Card card : cardsToCheck)
         {
             uniqueRanks.add(card.getRank());
         }

         ArrayList<IRank> ranks = new ArrayList<IRank>();
         ranks.addAll(uniqueRanks);
         return areRanksSequential(ranks);
     }

     /**
      * This tests whether the given list of cards is sequential.
      *
      * @param cardsToCheck {Card[]} - An ArrayList of cards to verify.
      * @return {boolean} - Whether the cards are consecutive.
      */
     private static boolean areCardsSequential(
         final ArrayList<Card> cardsToCheck
     )
     {
         ArrayList<IRank> ranks = new ArrayList<IRank>();
         for (Card card : cardsToCheck)
         {
             ranks.add(card.getRank());
         }
         return areRanksSequential(ranks);
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
             // For now, special cards mean false.
             if (!(firstRank instanceof StandardRank))
             {
                 return false;
             }
             // Everything larger than 1 is okay here.
             int delta = 2;
             for (IRank secondRank : ranksToCheck)
             {
                 // For now, special cards mean false.
                 if (!(firstRank instanceof StandardRank))
                 {
                     return false;
                 }

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
}
