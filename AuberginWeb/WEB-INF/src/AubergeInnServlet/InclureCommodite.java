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

public class InclureCommodite extends HttpServlet{

	private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
        {
            System.out.println("Servlet AjouterCommodite : POST");

            
            // lecture des paramètres du formulaire login.jsp
            
            String idChambre = request.getParameter("IdChambre");
            String idCommodite = request.getParameter("IdCommodite");
            
            request.setAttribute("IdChambre", idChambre);
            request.setAttribute("IdCommodite", idCommodite);
            
                        
            if(idCommodite == null || idCommodite.equals(""))
                throw new IFT287Exception("Vous devez entrer un CommoditeID.");
            
            if(idChambre == null || idChambre.equals(""))
                throw new IFT287Exception("Vous devez entrer un chambre ID.");

            
			AubergeInnHelper.getAubergeInnUpdate(request.getSession()).getGestionCommodite()
				.inclure(Integer.parseInt(idChambre), Integer.parseInt(idCommodite));
			
			request.setAttribute("id", idChambre.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ChambreDetails.jsp");
            dispatcher.forward(request, response);
            
        }
        catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/InclureCommodite.jsp");
            dispatcher.forward(request, response);
            // pour déboggage seulement : afficher tout le contenu de
            // l'exception
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/InclureCommodite.jsp");
            dispatcher.forward(request, response);
            // pour déboggage seulement : afficher tout le contenu de
            // l'exception
            e.printStackTrace();
        }
    }

    // Dans les formulaires, on utilise la méthode POST
    // donc, si le servlet est appelé avec la méthode GET
    // c'est que l'adresse a été demandé par l'utilisateur.
    // On procède si la connexion est actives seulement, sinon
    // on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet InclureCommodite");
        String str = request.getParameter("IdChambre").toString();
        request.setAttribute("IdChambre", request.getParameter("IdChambre"));
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/InclureCommodite.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }
}
