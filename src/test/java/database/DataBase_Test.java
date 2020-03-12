package taipan.database;

import org.junit.Assert;
import org.junit.Test;

public class DataBase_Test
{
    @Test
    public void test_init()
    {
        MongoDBConnection db = new MongoDBConnection();
    }

    @Test
    public void test_print()
    {
        MongoDBConnection db = new MongoDBConnection();
        db.addPlayer("FirstPlayer", "guesswhat");
        db.printCollection();
        db.removePlayer("FirstPlayer", "guesswhat");
    }
}
