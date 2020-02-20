package taipan.domain;

import java.util.ArrayList;

class Triple extends Play
{
    Triple(final ArrayList<Card> newCards)
    {
        super(newCards);
    }

    /**
     * Test whether this play beats the given play.
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
     * Calculates the value of this Play. I.e. the rank of the Triple.
     *
     * @return {int} - The value of this Play.
     */
    @Override
    protected int getValue()
    {
        return this.getCards().get(0).getRank().getValue();
    }
}
