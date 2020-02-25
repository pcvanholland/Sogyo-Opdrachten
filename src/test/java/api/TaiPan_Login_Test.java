package taipan.api;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaiPan_Login_Test
{
    @Test
    public void test_init()
    {
        new TaiPan();
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
        tp.register(request, player);

        Assert.assertEquals(200, tp.login(request, player).getStatus());
        tp.unregister(request, player);
    }

    @Test
    public void test_loginFailureDueToUnpresence()
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
    public void test_registerFailureDueToDuplicity()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        TaiPan tp = new TaiPan();
        tp.register(request, player);

        Assert.assertEquals(500, tp.register(request, player).getStatus());
        tp.unregister(request, player);
    }

    @Test
    public void test_unregister()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        TaiPan tp = new TaiPan();
        tp.register(request, player);

        Assert.assertEquals(200, tp.unregister(request, player).getStatus());
    }

    @Test
    public void test_unregisterFailureDueToUnprecense()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        TaiPan tp = new TaiPan();

        Assert.assertEquals(500, tp.unregister(request, player).getStatus());
    }

    @Test
    public void test_unregisterFailureDueToWrongPassword()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Player player = new Player();
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        TaiPan tp = new TaiPan();
        tp.register(request, player);
        player.setPassword("guasswhat");

        Assert.assertEquals(500, tp.unregister(request, player).getStatus());

        player.setPassword("guesswhat");
        tp.unregister(request, player);
    }
}
