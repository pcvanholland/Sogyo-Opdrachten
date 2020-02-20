package taipan.api;

/**
 * Represents the four players present.
 */
public class PlayerData
{
	private String[] playerNames = new String[4];

    /**
     * Adds a new player to the game.
     *
     * @param name {String} - The name of the player to add.
     */
    protected void addPlayer(String name)
    {
        for (int i = 0; i < playerNames.length; ++i)
        {
            if (playerNames[i] == null)
            {
                playerNames[i] = name;
                break;
            }
        }
    }

    /**
     * @return {boolean} - Whether this game is full.
     */
    protected boolean isGameFull()
    {
        boolean full = true;
        for (String player : playerNames)
        {
            if (player == null)
            {
                full = false;
                break;
            }
        }
        return full;
    }
}
