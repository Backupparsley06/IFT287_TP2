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

public class Reservation extends HttpServlet{

	private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
        {
            System.out.println("Servlet AjouterClient : POST");

            
            // lecture des param�tres du formulaire login.jsp
            String idClient = request.getParameter("IdClient");
            String idChambre = request.getParameter("IdChambre");
            String dateDebut = request.getParameter("dateDebut");
            String dateFin = request.getParameter("dateFin");
            
            request.setAttribute("IdClient", idClient);
            request.setAttribute("IdChambre", idChambre);
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
                        
            if(idClient == null || idClient.equals(""))
                throw new IFT287Exception("Vous devez entrer un Client ID.");
            
            if(idChambre == null || idChambre.equals(""))
                throw new IFT287Exception("Vous devez entrer un chambre ID.");
            
            if(dateDebut == null || dateDebut.equals(""))
                throw new IFT287Exception("Vous devez entrer une date de debut.");
            
            if(dateFin == null || dateFin.equals(""))
                throw new IFT287Exception("Vous devez entrer une date de fin.");
            
            
			AubergeInnHelper.getAubergeInnUpdate(request.getSession()).getGestionReservation()
				.reserver(Integer.parseInt(idClient), Integer.parseInt(idChambre), java.sql.Date.valueOf(dateDebut), java.sql.Date.valueOf(dateFin));
			
			request.setAttribute("id", idClient);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/ClientDetails.jsp");
            dispatcher.forward(request, response);
            
        }
        catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Reservation.jsp");
            dispatcher.forward(request, response);
            // pour d�boggage seulement : afficher tout le contenu de
            // l'exception
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Reservation.jsp");
            dispatcher.forward(request, response);
            // pour d�boggage seulement : afficher tout le contenu de
            // l'exception
            e.printStackTrace();
        }
    }

    // Dans les formulaires, on utilise la m�thode POST
    // donc, si le servlet est appel� avec la m�thode GET
    // c'est que l'adresse a �t� demand� par l'utilisateur.
    // On proc�de si la connexion est actives seulement, sinon
    // on retourne au login
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Servlet Reservation");
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
        	String idClient = request.getParameter("IdClient");
        	request.setAttribute("IdClient", idClient);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Reservation.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }
}
