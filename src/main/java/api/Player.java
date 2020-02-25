package taipan.api;

/**
 * This class is used to add or remove a player from the database.
 */
public class Player
{
    private String name;
    private String password;

    /**
     * @return {String} - The name of this Player.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return {String} - The password of this Player.
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Sets this Player's name.
     * @param newName {String} - The new name of this Player.
     */
    public void setName(final String newName)
    {
        this.name = newName;
    }

    /**
     * Sets this Player's password.
     * @param newPassword {String} - The new password of this Player.
     */
    public void setPassword(final String newPassword)
    {
        this.password = newPassword;
    }
}
