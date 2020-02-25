package taipan.api;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaiPan_Test
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

        TaiPan tp = new TaiPan();

        Assert.assertEquals(200, tp.play(request).getStatus());
    }

    @Test
    public void test_loginSuccess()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        TaiPan tp = new TaiPan();

        Assert.assertEquals(200, tp.login(request, player).getStatus());
    }

    @Test
    public void test_loginFailure()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");
        player.setPassword("guasswhat");

        TaiPan tp = new TaiPan();

        Assert.assertEquals(500, tp.login(request, player).getStatus());
    }

    @Test
    public void test_join()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        TaiPan tp = new TaiPan();

        Assert.assertEquals(200, tp.joinGame(request).getStatus());
    }
}
