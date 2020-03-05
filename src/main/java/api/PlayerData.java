package taipan.api;

/**
 * Represents the four players present.
 */
public class PlayerData
{
    protected static final int MAX_NUMBER_OF_PLAYERS = 4;
    private String[] playerNames = new String[MAX_NUMBER_OF_PLAYERS];

    /**
     * Adds a new player to the game.
     *
     * @param name {String} - The name of the player to add.
     * @return {int} - The playerID of the Player.
     */
    protected int addPlayer(final String name)
    {
        for (int i = 0; i < playerNames.length; ++i)
        {
            if (playerNames[i] == null)
            {
                playerNames[i] = name;
                return i;
            }
        }
        return -1;
    }

    /**
     * @return {boolean} - Whether this game is full.
     */
    protected boolean isGameFull()
    {
        for (String player : playerNames)
        {
            if (player == null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * @param playerNumber {int} - The Player to get the name of.
     * @return {int} - The name of the specified Player.
     */
    protected String getPlayerName(final int playerNumber)
    {
        return this.playerNames[playerNumber];
    }
}
