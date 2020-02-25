package taipan.api;

public class Player
{
    private String name;
    private String password;

    public String getName()
    {
        return this.name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setName(final String newName)
    {
        this.name = newName;
    }

    public void setPassword(final String newPassword)
    {
        this.password = newPassword;
    }
}
