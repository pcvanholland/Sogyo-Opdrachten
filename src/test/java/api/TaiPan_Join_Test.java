package taipan.api;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaiPan_Join_Test
{
    @Test
    public void test_init()
    {
        new TaiPan();
    }

    @Test
    public void test_join()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");

        Lobby tp = new Lobby();

        Assert.assertEquals(200, tp.joinGame(request, player).getStatus());
    }
}
