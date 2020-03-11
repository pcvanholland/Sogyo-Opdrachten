package taipan.domain;

import java.util.ArrayList;

public interface ITable
{
    /**
     * @return {Play[]} - The current Plays that have been played.
     */
    ArrayList<Play> getCurrentPlays();
}
