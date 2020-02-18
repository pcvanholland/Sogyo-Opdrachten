package taipan.domain;

import java.util.ArrayList;

class Play
{
    ArrayList<Card> cards;

    /**
     * Constructor for a play (a set of cards played).
     *
     * @param {Card[]} cards - An ArrayList of cards bundled in this play.
     */
    Play(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    /**
     * This tests whether a given play is valid.
     * Regardless of the cards in a Trick.
     *
     * @param {Card[]} cards - The ArrayList of cards to verify.
     * @return {boolean} - The validity of the tested play.
     */
    protected static boolean isValidPlay(ArrayList<Card> cards)
    {
        return areCardsOfEqualRank(cards) ||
            areCardsASetOfDoubles(cards);
    }

    /**
     * This tests whether the provided cards are of equal rank.
     *
     * @param {Card[]} cards - The ArrayList of cards to verify.
     * @return {boolean} - Whether *all* the provided cards are of equal rank.
     */
    private static boolean areCardsOfEqualRank(ArrayList<Card> cards)
    {
        IRank firstRank = cards.get(0).getRank();
        for (Card card : cards)
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
     * @param {Card[]} cards - The ArrayList of cards to verify.
     * @return {boolean} - Whether the provided cards are a set of sets.
     */
    private static boolean areCardsASetOfDoubles(ArrayList<Card> cards)
    {
        // An odd-sized array is never a valid set.
        if (cards.size() % 2 != 0)
        {
            return false;
        }

        ArrayList<IRank> ranks = new ArrayList<IRank>();
        for (Card card : cards)
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
            int diff = 15;
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

                if (Math.abs(firstValue - secondValue) < diff)
                {
                    diff = Math.abs(firstValue - secondValue);
                }
            }
            if (diff > delta)
            {
                delta = diff;
            }
        }
        if (delta > 1)
        {
            return false;
        }

        return true;
    }
}
