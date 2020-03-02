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
}
