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
    public void test_additionFailsDueToPresence()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");
        Assert.assertFalse(db.addPlayer("FirstPlayer", "guesswhat"));
    }

    @Test
    public void test_print()
    {
        DataBase db = new DataBase();
        db.printCollection();
    }

    @Test
    public void test_goodPassword()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");
        Assert.assertTrue(db.verifyPassword("FirstPlayer", "guesswhat"));
        Assert.assertFalse(db.verifyPassword("FirsPlayer", "guesswhat"));
        Assert.assertFalse(db.verifyPassword("FirstPlayer", "guasswhat"));
    }

    @Test
    public void test_wrongUsername()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");
        Assert.assertFalse(db.verifyPassword("FirsPlayer", "guesswhat"));
    }

    @Test
    public void test_wrongPassword()
    {
        DataBase db = new DataBase();
        db.addPlayer("FirstPlayer", "guesswhat");
        Assert.assertFalse(db.verifyPassword("FirstPlayer", "guasswhat"));
    }
}
