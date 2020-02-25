package taipan.api;

import org.junit.Assert;
import org.junit.Test;

public class DataBase_Test
{
    @Test
    public void test_init()
    {
        DataBase db = new DataBase();
    }

    @Test
    public void test_print()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");
        db.printCollection();
        db.removePlayer("FirstPlayer", "guesswhat");
    }

    @Test
    public void test_goodPassword()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");

        Assert.assertTrue(db.verifyPassword("FirstPlayer", "guesswhat"));

        db.removePlayer("FirstPlayer", "guesswhat");
    }

    @Test
    public void test_wrongUsername()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");

        Assert.assertFalse(db.verifyPassword("FirsPlayer", "guesswhat"));

        db.removePlayer("FirstPlayer", "guesswhat");
    }

    @Test
    public void test_wrongPassword()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");

        Assert.assertFalse(db.verifyPassword("FirstPlayer", "guasswhat"));

        db.removePlayer("FirstPlayer", "guesswhat");
    }
}
