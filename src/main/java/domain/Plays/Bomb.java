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
        return this.getClass() != playToBeat.getClass() ||
            this.beatsBomb((Bomb) playToBeat);
    }

    /**
     * Test whether this Bomb beats the given Bomb.
     *
     * @param bombToBeat {Bomb} - The Bomb to beat.
     * @return {boolean} - Whether this Bomb is higher in rank than the given.
     */
    private boolean beatsBomb(final Bomb bombToBeat)
    {
        return this.getLength() > bombToBeat.getLength() ||
            (this.getLength() == bombToBeat.getLength() &&
            this.getValue() > bombToBeat.getValue());
    }
}
