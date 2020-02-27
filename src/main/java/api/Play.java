package taipan.api;

/**
 * This class is used to perform a Play.
 */
public class Play
{
    private String playerID;
    private String cards;
    private String type;

    /**
     * @return {String} - The ID of this Player.
     */
    public String getPlayerID()
    {
        return this.playerID;
    }

    /**
     * @return {String} - The cards of this Play.
     */
    public String getCards()
    {
        return this.cards;
    }

    /**
     * @return {String} - The type of this Play.
     */
    public String getType()
    {
        return this.type;
    }

    /**
     * Sets this Player's ID.
     * @param newPlayerID {String} - The ID belonging to this Play.
     */
    public void setPlayerID(final String newPlayerID)
    {
        this.playerID = newPlayerID;
    }

    /**
     * Sets this Plays Cars.
     * @param newCards {String} - The Cards of this Play.
     */
    public void setCards(final String newCards)
    {
        this.cards = newCards;
    }

    /**
     * Sets this Play's type.
     * @param newType {String} - The type of this Play.
     */
    public void setType(final String newType)
    {
        this.type = newType;
    }
}
