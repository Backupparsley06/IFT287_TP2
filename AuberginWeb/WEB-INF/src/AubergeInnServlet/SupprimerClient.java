package AubergeInnServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AubergeInn.IFT287Exception;

public class SupprimerClient extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet SupprimerClient : POST");
    }

    // Dans les formulaires, on utilise la m�thode POST
    // donc, si le servlet est appel� avec la m�thode GET
    // c'est que l'adresse a �t� demand� par l'utilisateur.
    // On proc�de si la connexion est actives seulement, sinon
    // on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet SupprimerClient : GETGET id:" + request.getParameter("id").toString());
        int idClient = Integer.parseInt(request.getParameter("id").toString());
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
        	try {
				AubergeInnHelper.getAubergeInnUpdate(request.getSession()).getGestionClient().supprimer(idClient);
        	}
	        catch (IFT287Exception e)
	        {
	            List<String> listeMessageErreur = new LinkedList<String>();
	            listeMessageErreur.add(e.getMessage());
	            request.setAttribute("listeMessageErreur", listeMessageErreur);
	            e.printStackTrace();
	        }
	        catch (SQLException e)
	        {
	            List<String> listeMessageErreur = new LinkedList<String>();
	            listeMessageErreur.add(e.getMessage());
	            request.setAttribute("listeMessageErreur", listeMessageErreur);
	            e.printStackTrace();
	        }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionclients.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }

} 