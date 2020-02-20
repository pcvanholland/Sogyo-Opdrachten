package taipan.api;

import org.junit.Assert;
import org.junit.Test;

public class PlayerData_Test
{
    @Test
    public void test_init()
    {
        new PlayerData();
    }

    @Test
    public void test_gameNotFull()
    {
        PlayerData pd = new PlayerData();
        for (int i = 0; i < 3; ++i)
        {
            pd.addPlayer("Name");
        }

        Assert.assertFalse(pd.isGameFull());
    }

    @Test
    public void test_gameFull()
    {
        PlayerData pd = new PlayerData();
        for (int i = 0; i < 4; ++i)
        {
            pd.addPlayer("Name");
        }

        Assert.assertTrue(pd.isGameFull());
    }
}
