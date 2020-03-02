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
        new TaiPan(Player_Test.SEED);
    }

    @Test
    public void test_play()
    {
        int playerID = 0;
        TaiPan tp = new TaiPan(Player_Test.SEED);
        tp.letPlayerDrawCards(playerID);
        tp.letPlayerDrawCards(playerID);

        tp.play(playerID, "[\"SPECIAL,MAHJONG\"]", "SINGLE");
        Assert.assertEquals(13, tp.getPlayer(playerID).getCards().size());
    }
}
