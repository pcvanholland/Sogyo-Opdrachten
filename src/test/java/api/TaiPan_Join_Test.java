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

        Lobby tp = new Lobby();

        //Assert.assertEquals(200, tp.startGane(request).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.joinGame(request).getStatus());
    }

    @Test
    public void test_start()
    {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Lobby tp = new Lobby();

        //Assert.assertEquals(200, tp.startGane(request).getStatus());

        // No session,,,
        Assert.assertEquals(500, tp.startGame(request).getStatus());
    }
}
