package taipan.domain;

import java.util.ArrayList;

class Table
{
    private Trick trick;

    /**
     * @return {Trick} - The current Trick that is being played.
     */
    Trick getCurrentTrick()
    {
        return this.trick;
    }

    /**
     * @return {Play} - The last Play on the Table.
     */
    Play getLastPlay()
    {
        return this.getCurrentTrick().getLastPlay();
    }

    /**
     * @return {Play[]} - The current Plays that have been played.
     */
    ArrayList<Play> getCurrentPlays()
    {
        if (this.getCurrentTrick() == null)
        {
            return new ArrayList<Play>();
        }
        return this.getCurrentTrick().getPlays();
    }

    /**
     * Puts a Play on the Table.
     * When no Trick was present yet, it creates one.
     *
     * @param play {Play} - The Play to put on the Table.
     */
    void play(final Play play) throws CantPlayTableException
    {
        if (!this.canPlay(play))
        {
            throw new CantPlayTableException();
        }
        if (this.getCurrentTrick() == null)
        {
            this.lead(play);
        }
        else
        {
            this.getCurrentTrick().play(play);
        }
    }

    /**
     * Starts a new Trick.
     *
     * @param play {Play} - The Play to lead the Trick with.
     */
    private void lead(final Play play)
    {
        this.trick = new Trick(play);
    }

    /**
     * This checks whether the provided Play can be played.
     * I.e. whether there was no Trick yet or it (^) beats the last Play.
     *
     * @param play {Play} - The Play to check.
     * @return {Boolean} - Whether the provided Play can be played.
     */
    boolean canPlay(final Play play)
    {
        return this.getCurrentTrick() == null ||
            play.beats(this.getLastPlay());
    }

    /**
     * Takes the current Trick and gives it to the asker.
     *
     * @return {Trick} - The current Trick.
     */
    Trick giveTrick()
    {
        Trick trickToGive = this.getCurrentTrick();
        this.trick = null;

        return trickToGive;
    }
}
