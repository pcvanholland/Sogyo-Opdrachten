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
    public void test_start()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Lobby tp = new Lobby();

        //Assert.assertEquals(200, tp.startGane(request).getStatus());

        // No session,,,
        Assert.assertEquals(
            500,
            tp.startGame(request, "FirstPlayer").getStatus()
        );
    }

    @Test
    public void test_join()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Lobby tp = new Lobby();
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setPlayerName("SecondPlayer");
        joinRequest.setGameID(0);

        //Assert.assertEquals(200, tp.startGane(request).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.joinGame(request, joinRequest).getStatus());
    }

    @Test
    public void test_cantJoinFullGame()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Lobby tp = new Lobby();
        tp.startGame(request, "FirstPlayer");
        for (int i = 0; i < PlayerData.MAX_NUMBER_OF_PLAYERS - 1; ++i)
        {
            JoinRequest joinRequest = new JoinRequest();
            joinRequest.setPlayerName("Player " + i);
            joinRequest.setGameID(0);
            tp.joinGame(request, joinRequest);
        }
        JoinRequest joinRequest = new JoinRequest();
        joinRequest.setPlayerName("TooLatePlayer");
        joinRequest.setGameID(0);

        Assert.assertEquals(500, tp.joinGame(request, joinRequest).getStatus());
    }

    @Test
    public void test_listGames()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Lobby tp = new Lobby();

        //Assert.assertEquals(200, tp.startGane(request).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.listGames(request).getStatus());
    }
}
