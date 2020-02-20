package taipan.domain;

import java.util.ArrayList;

class Single extends Play
{
    Single(final ArrayList<Card> newCards)
    {
        super(newCards);
    }

    /**
     * Test whether this card beats the given card.
     *
     * @param playToBeat {Play} - The play to beat.
     * @return {boolean} - Whether this play is higher in rank than the given.
     */
    @Override
    protected boolean beats(final Play playToBeat)
    {
        return this.isSameSetAs(playToBeat) &&
            this.getValue() > playToBeat.getValue();
    }

    /**
     * Returns the rank of this Card.
     *
     * @return {int} - The value of this Play.
     */
    @Override
    protected int getValue()
    {
        return this.getCards().get(0).getRank().getValue();
    }
}
