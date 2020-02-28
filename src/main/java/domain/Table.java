package taipan.domain;

class Table
{
    private Trick trick;

    /**
     * @return {Trick} - The current Trick that is being played.
     */
    protected Trick getCurrentTrick()
    {
        return this.trick;
    }

    /**
     * Starts a new Trick.
     *
     * @param play {Play} - The Play to lead the Trick with.
     */
    protected void lead(final Play play)
    {
        this.trick = new Trick(play);
    }

    /**
     * @return {Play} - The last Play on the Table.
     */
    protected Play getLastPlay()
    {
        return this.getCurrentTrick().getLastPlay();
    }

    /**
     * @param play {Play} - The Play to put on the Table.
     */
    protected void play(final Play play)
    {
        this.getCurrentTrick().play(play);
    }
}
