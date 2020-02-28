package taipan.domain;

import java.util.ArrayList;

class Trick
{
    private ArrayList<Play> plays;

    /**
     * Constructor for a Trick. Beginning with the lead Play.
     *
     * @param play {Play} - The Play to lead this Trick with.
     */
    Trick(final Play play)
    {
        plays = new ArrayList<Play>();
        plays.add(play);
    }

    /**
     * @return {Play} - The last Play that was played.
     */
    protected Play getLastPlay()
    {
        return plays.get(plays.size() - 1);
    }

    /**
     * Tries to play a Play.
     *
     * @param newPlay {Play} - The Play to put on top of this Trick.
     */
    protected void play(final Play play)
    {
        plays.add(play);
    }
}
