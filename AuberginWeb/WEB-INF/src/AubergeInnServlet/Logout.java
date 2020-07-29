package AubergeInnServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // invalider la session pour libérer les ressources associées à la
        // session
        request.getSession().invalidate();
        //RequestDispatcher dispatcher = request.getRequestDispatcher("Accueil");
        //dispatcher..forward(request, response);
        getServletContext().setAttribute("serveur", null);
        AubergeInnHelper.DispatchToBDConnect(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        AubergeInnHelper.DispatchToBDConnect(request, response);
    }
}
