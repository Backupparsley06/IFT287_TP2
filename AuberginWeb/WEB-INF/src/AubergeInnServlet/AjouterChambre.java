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

public class AjouterChambre extends HttpServlet{

	private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
        {
            System.out.println("Servlet AjouterClient : POST");

            
            // lecture des paramètres du formulaire login.jsp
    
            String idChambre = request.getParameter("IdChambre");
            String nom = request.getParameter("Nom");
            String typeLit = request.getParameter("TypeLit");
            String prixBase = request.getParameter("PrixBase");
            
            request.setAttribute("IdChambre", idChambre);
            request.setAttribute("Nom", nom);
            request.setAttribute("TypeLit", typeLit);
            request.setAttribute("PrixBase", prixBase);
                        
            if(idChambre == null || idChambre.equals(""))
                throw new IFT287Exception("Vous devez entrer un ChambreID.");
            
            if(prixBase == null || prixBase.equals(""))
                throw new IFT287Exception("Vous devez entrer un prix de base.");

            
			AubergeInnHelper.getAubergeInnUpdate(request.getSession()).getGestionChambre()
				.ajouter(Integer.parseInt(idChambre), nom, typeLit, Double.parseDouble(prixBase));
			
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionchambres.jsp");
            dispatcher.forward(request, response);
            
        }
        catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AjouterChambre.jsp");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AjouterChambre.jsp");
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
        System.out.println("Servlet AjouterChambre");
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AjouterChambre.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }
}
