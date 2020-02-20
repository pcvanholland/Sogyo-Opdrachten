package taipan.domain;

import java.util.ArrayList;

class Bomb extends Play
{
    Bomb(final ArrayList<Card> newCards)
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
     * Calculates the value of this Play.
     * For a Street-Bomb the length of the street matters,
     * besides the highest card.
     *
     * @return {int} - The value of this Play.
     */
    @Override
    protected int getValue()
    {
        return 0;
    }
}
