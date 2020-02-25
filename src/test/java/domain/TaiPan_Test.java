package taipan.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TaiPan_Test
{
    @Test
    public void test_init()
    {
        new TaiPan();
    }

    @Test
    public void test_getEmptyGameState()
    {
        TaiPan tp = new TaiPan();
        ArrayList<ArrayList<Card>> expectedResult =
            new ArrayList<ArrayList<Card>>();
        for (int i = 0; i < 4; ++i)
        {
            expectedResult.add(new ArrayList<Card>());
        }

        Assert.assertEquals(expectedResult, tp.getGameState());
    }

    @Test
    public void test_getNonEmptyGameState()
    {
        TaiPan tp = new TaiPan();
        tp.letPlayerDrawCards(0);
        ArrayList<ArrayList<Card>> expectedResult =
            new ArrayList<ArrayList<Card>>();
        for (int i = 0; i < 4; ++i)
        {
            expectedResult.add(new ArrayList<Card>());
        }

        Assert.assertNotEquals(expectedResult, tp.getGameState());
    }
}
