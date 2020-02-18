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
            areCardsASetOfDoubles(cardsToVerify);
    }

    /**
     * This tests whether the provided cards are of equal rank.
     *
     * @param cardsToCheck {Card[]} - The ArrayList of cards to verify.
     * @return {boolean} - Whether *all* the provided cards are of equal rank.
     */
    private static boolean areCardsOfEqualRank(final ArrayList<Card> cardsToCheck)
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
    private static boolean areCardsASetOfDoubles(final ArrayList<Card> cardsToCheck)
    {
        // An odd-sized array is never a valid set.
        if (cardsToCheck.size() % 2 != 0)
        {
            return false;
        }

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
        java.util.HashSet<IRank> uniqueRanks = new java.util.HashSet<IRank>();
        uniqueRanks.addAll(ranks);

        int delta = 0;
        for (IRank firstRank : uniqueRanks)
        {
            // Everything larger than 1 is okay here.
            int diff = 2;
            for (IRank secondRank : uniqueRanks)
            {
                if (secondRank == firstRank)
                {
                    continue;
                }

                // We can safely cast (for now) because the special
                // cards are filtered out above.
                int firstValue = ((StandardRank) firstRank).getValue();
                int secondValue = ((StandardRank) secondRank).getValue();

                diff = Math.min(Math.abs(firstValue - secondValue), diff);
            }
            delta = Math.max(diff, delta);
        }
        if (delta > 1)
        {
            return false;
        }

        return true;
    }
}
