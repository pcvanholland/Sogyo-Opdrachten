package taipan.api;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaiPan_Play_Test
{
    @Test
    public void test_init()
    {
        new TaiPan();
    }

    @Test
    public void test_play()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");

        TaiPan tp = new TaiPan();

        Assert.assertEquals(200, tp.play(request, player).getStatus());
    }

    @Test
    public void test_getGameState()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        TaiPan tp = new TaiPan();
        //Lobby lobby = new Lobby();
        //lobby.startGame(request);

        //Assert.assertEquals(200, tp.getGameState(request).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.getGameState(request).getStatus());
    }

    @Test
    public void test_drawCards()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        TaiPan tp = new TaiPan();
        //Lobby lobby = new Lobby();
        //lobby.startGame(request);

        //Assert.assertEquals(200, tp.drawCards(request).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.drawCards(request, 0).getStatus());
    }
}
