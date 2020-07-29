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

public class EnleverCommodite extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet EnleverCommodite : POST");
    }

    // Dans les formulaires, on utilise la méthode POST
    // donc, si le servlet est appelé avec la méthode GET
    // c'est que l'adresse a été demandé par l'utilisateur.
    // On procède si la connexion est actives seulement, sinon
    // on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet EnleverCommodite : GET");
        String idChambre = request.getParameter("IdChambre");
        String idCommodite = request.getParameter("IdCommodite");
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
        	try {
				AubergeInnHelper.getAubergeInnUpdate(request.getSession()).getGestionCommodite().enlever(Integer.parseInt(idChambre), Integer.parseInt(idCommodite));
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
			request.setAttribute("id", idChambre.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ChambreDetails.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }

} 