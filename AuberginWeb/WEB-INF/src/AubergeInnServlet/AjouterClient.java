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

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;

public class AjouterClient extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
        {
            System.out.println("Servlet AjouterClient : POST");

            
            // lecture des paramètres du formulaire login.jsp
    
            String iDClient = request.getParameter("IdClient");
            String nom = request.getParameter("Nom");
            String prenom = request.getParameter("Prenom");
            String age = request.getParameter("Age");
            
            request.setAttribute("IdClient", iDClient);
            request.setAttribute("Nom", nom);
            request.setAttribute("Prenom", prenom);
            request.setAttribute("Age", age);
                        
            if(iDClient == null || iDClient.equals(""))
                throw new IFT287Exception("Vous devez entrer un ClientId.");
            
            if(age == null || age.equals(""))
                throw new IFT287Exception("Vous devez entrer un age.");

            
			AubergeInnHelper.getAubergeInnUpdate(request.getSession()).getGestionClient()
				.ajouter(Integer.parseInt(iDClient), nom, prenom, Integer.parseInt(age));
			
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/gestionclients.jsp");
            dispatcher.forward(request, response);
            
        }
        catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AjouterClient.jsp");
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AjouterClient.jsp");
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
        System.out.println("Servlet AjouterClient");
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AjouterClient.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }

} 
