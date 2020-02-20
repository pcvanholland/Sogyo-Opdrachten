package taipan.domain;

import java.util.ArrayList;

class FullHouse extends Play
{
    private Triple triple;
    private Set set;

    FullHouse(final ArrayList<Card> newCards)
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
        return true;
    }

    /**
     * Calculates the value of this Play. I.e. the rank of the triple.
     *
     * @return {int} - The value of this Play.
     */
    @Override
    protected int getValue()
    {
        return 0;
    }
}
