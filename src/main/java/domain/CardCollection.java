package taipan.domain;

import java.util.ArrayList;

/**
 * This is a general class representing a collection of Cards.
 * It contains some common functions useful to any collection.
 */
class CardCollection
{
    private ArrayList<Card> cards;

    /**
     * Default constructor for a collection of Cards.
     */
    CardCollection()
    {
        this.cards = new ArrayList<Card>();
    }

    /**
     * Adds a Card to this collection.
     * If a Phoenix is present in the current list of Cards
     * it will be automagically convert to a sensibly value,
     * if applicable.
     *
     * @param card {Card} - The Card to add.
     */
    final void add(final Card card)
    {
        this.cards.add(card);
        this.updatePhoenixValue();
    }

    /**
     * @return {int} - The size of the Cards.
     */
    final int size()
    {
        return this.cards.size();
    }

    /**
     * @return {Card[]} - The Cards present in this collection.
     */
    ArrayList<Card> getCards()
    {
        return this.cards;
    }

    /**
     * @param position {int} - The position to get the Card from.
     * @return {Card} - The Card at the specified position.
     */
    final Card get(final int position)
    {
        return this.cards.get(position);
    }

    /**
     * Determines which types of Play this set of Cards can be.
     *
     * @return {Set[]} - An ArrayList of possible sets to make.
     */
    final ArrayList<Set> determineTypesOfSet()
    {
        ArrayList<Set> validSets = new ArrayList<Set>();
        if (Single.isSingle(this))
        {
            validSets.add(Set.SINGLE);
        }

        if (Pair.isPair(this))
        {
            validSets.add(Set.PAIR);
        }

        if (Triple.isTriple(this))
        {
            validSets.add(Set.TRIPLE);
        }

        if (Stair.isStair(this))
        {
            validSets.add(Set.STAIR);
        }

        if (Straight.isStraight(this))
        {
            validSets.add(Set.STRAIGHT);
        }

        if (FullHouse.isFullHouse(this))
        {
            validSets.add(Set.FULLHOUSE);
        }

        if (Bomb.isBomb(this))
        {
            validSets.add(Set.BOMB);
        }

        return validSets;
    }

    /**
     * This tests whether these Cards are of equal Rank.
     *
     * @return {boolean} - Whether *all* the provided Cards are of equal rank.
     */
    final boolean areCardsOfEqualRank()
    {
        return this.containsOnlyNumberOfEqualRanks(this.cards.size());
    }

    /**
     * This tests whether these Cards are of equal Suit.
     *
     * @return {boolean} - Whether *all* the provided Cards are of equal Suit.
     */
    final boolean areCardsOfEqualSuit()
    {
        ISuit firstSuit = this.get(0).getSuit();
        for (Card card : this.cards)
        {
            if (card.getSuit() != firstSuit)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This tests whether the list of Cards contains any Pair.
     *
     * @return {boolean} - Whether the list contains a Pair.
     */
    final boolean containsPair()
    {
        return this.containsNumberOfEqualRanks(2);
    }

    /**
     * This tests whether the list of Cards contains any Triple.
     *
     * @return {boolean} - Whether the list contains a Triple.
     */
    final boolean containsTriple()
    {
        return this.containsNumberOfEqualRanks(Triple.TRIPLE_SIZE);
    }

    /**
     * This tests whether the list of Cards contains at least once
     * exactly the specified amount of Cards with the same Rank.
     *
     * @param amount {int} - How many of equal Rank may be present.
     *
     * @return {boolean} - Whether the list contains the specified multiplicity.
     */
    private boolean containsNumberOfEqualRanks(final int amount)
    {
        ArrayList<Integer> ranks = this.getRanks();
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
     * This tests whether the list of Cards only contains the
     * specified number of equal Ranks.
     *
     * @param amount {int} - How many of equal Rank must be present.
     *
     * @return {boolean} - Whether the list only contains the given Rank amount.
     */
    final boolean containsOnlyNumberOfEqualRanks(final int amount)
    {
        ArrayList<Integer> ranks = this.getRanks();
        for (Integer rank : ranks)
        {
            if (java.util.Collections.frequency(ranks, rank) != amount)
            {
                return false;
            }
        }
        return this.size() > 0;
    }

    /**
     * This tests whether the list of Cards is sequential.
     *
     * @return {boolean} - Whether the Cards are consecutive.
     */
    final boolean areCardsSequential()
    {
        return this.areRanksSequential(this.getRanks());
    }

    /**
     * This tests whether the given array of Ranks is sequential.
     *
     * @param ranksToCheck {IRank[]} - An ArrayList of Ranks to verify.
     * @return {boolean} - Whether all the Ranks are consecutive.
     */
    private boolean areRanksSequential(
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
     * @return {IRank[]} - An ArrayList of the corresponding Ranks.
     */
    final ArrayList<Integer> getRanks()
    {
        ArrayList<Integer> ranks = new ArrayList<Integer>();
        for (Card card : this.cards)
        {
            ranks.add(card.getValue());
        }
        return ranks;
    }

    /**
     * Creates a proper Play of these Cards.
     *
     * @param owner {Player} - The Player who wants to Play the Play.
     * @param set {Set} - The type of Play to use.
     *
     * @return {Play} - The Play the Cards have become.
     */
    final Play createPlay(
        final Player owner,
        final Set set
    )
    {
        if (set.equals(Set.SINGLE))
        {
            return new Single(this, owner);
        }
        if (set.equals(Set.PAIR))
        {
            return new Pair(this, owner);
        }
        if (set.equals(Set.TRIPLE))
        {
            return new Triple(this, owner);
        }
        if (set.equals(Set.STAIR))
        {
            return new Stair(this, owner);
        }
        if (set.equals(Set.FULLHOUSE))
        {
            return new FullHouse(this, owner);
        }
        if (set.equals(Set.STRAIGHT))
        {
            return new Straight(this, owner);
        }
        if (set.equals(Set.BOMB))
        {
            return new Bomb(this, owner);
        }
        // We can't arrive here.
        throw new InvalidPlayException();
    }

    /**
     * Updates the value of the Phoenix according to its current context.
     */
    private void updatePhoenixValue()
    {
        for (Card card : this.getCards())
        {
            if (card instanceof Phoenix)
            {
                ((Phoenix) card).updateValueInSet(this);
            }
        }
    }
}
