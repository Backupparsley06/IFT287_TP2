package AubergeInnServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GestionClients extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet GestionClients : POST");
    }

    // Dans les formulaires, on utilise la m�thode POST
    // donc, si le servlet est appel� avec la m�thode GET
    // c'est que l'adresse a �t� demand� par l'utilisateur.
    // On proc�de si la connexion est actives seulement, sinon
    // on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet GestionClients : GET");
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionclients.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }

} 