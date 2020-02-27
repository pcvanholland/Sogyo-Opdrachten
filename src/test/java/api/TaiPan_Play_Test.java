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
    public void test_getPlayTypes()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        String play = "test";

        TaiPan tp = new TaiPan();

        //Assert.assertEquals(200, tp.getPlayTypes(request, play).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.getPlayTypes(request, play).getStatus());
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

        //Assert.assertEquals(200, tp.drawCards(request, 0).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.drawCards(request, 0).getStatus());
    }

    @Test
    public void test_playCards()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        TaiPan tp = new TaiPan();
        Play play = new Play();
        play.setPlayerID("0");
        play.setType("TEST");
        play.setCards("cards");

        //Lobby lobby = new Lobby();
        //lobby.startGame(request);

        //Assert.assertEquals(200, tp.playCards(request, play).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.playCards(request, play).getStatus());
    }
}
