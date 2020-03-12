package taipan.api;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authentication_Test
{
    @Test
    public void test_init()
    {
        new Authentication();
    }

    @Test
    public void test_loginSuccess()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        PlayerRequest  player = new PlayerRequest() ;
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        Authentication auth = new Authentication();
        auth.register(request, player);

        Assert.assertEquals(200, auth.login(request, player).getStatus());
        auth.unregister(request, player);
    }

    @Test
    public void test_loginFailureDueToUnpresence()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        PlayerRequest  player = new PlayerRequest() ;
        player.setName("FirstPlayer");
        player.setPassword("guasswhat");

        Authentication auth = new Authentication();

        Assert.assertEquals(500, auth.login(request, player).getStatus());
    }

    @Test
    public void test_registerFailureDueToDuplicity()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        PlayerRequest  player = new PlayerRequest() ;
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        Authentication auth = new Authentication();
        auth.register(request, player);

        Assert.assertEquals(500, auth.register(request, player).getStatus());
        auth.unregister(request, player);
    }

    @Test
    public void test_unregister()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        PlayerRequest  player = new PlayerRequest() ;
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        Authentication auth = new Authentication();
        auth.register(request, player);

        Assert.assertEquals(200, auth.unregister(request, player).getStatus());
    }

    @Test
    public void test_unregisterFailureDueToUnprecense()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        PlayerRequest  player = new PlayerRequest() ;
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        Authentication auth = new Authentication();

        Assert.assertEquals(500, auth.unregister(request, player).getStatus());
    }

    @Test
    public void test_unregisterFailureDueToWrongPassword()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        PlayerRequest  player = new PlayerRequest() ;
        player.setName("FirstPlayer");
        player.setPassword("guesswhat");

        Authentication auth = new Authentication();
        auth.register(request, player);
        player.setPassword("guasswhat");

        Assert.assertEquals(500, auth.unregister(request, player).getStatus());

        player.setPassword("guesswhat");
        auth.unregister(request, player);
    }
}
