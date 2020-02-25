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
}
