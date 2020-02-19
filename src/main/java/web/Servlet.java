package taipan.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet
{
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=ascii");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
        String name = request.getParameter("user");
        out.print("Was a get.");
        out.close();
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=ascii");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
        String name = request.getParameter("playerName");
        System.out.println(name);
        out.print("Was a post.");
        out.close();
    }

    @Override
	public void init() throws ServletException
    {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy()
    {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
}
