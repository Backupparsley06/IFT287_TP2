package AubergeInnServlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;


public class Accueil extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try
        {
            System.out.println("Servlet Login : POST");
            // Si on a d�j� entr� les informations de connexion valide
            if (AubergeInnHelper.infoBDValide(getServletContext()))
            {
            	AubergeInnHelper.DispatchToAccueil(request, response);
                return;
            }
            
            // lecture des param�tres du formulaire login.jsp
            String userId = request.getParameter("userIdBD");
            String motDePasse = request.getParameter("motDePasseBD");
            String serveur = request.getParameter("serveur");
            String bd = request.getParameter("bd");
            
            request.setAttribute("userIdBD", userId);
            request.setAttribute("motDePasseBD", motDePasse);
            request.setAttribute("serveur", serveur);
            request.setAttribute("bd", bd);
                        
            if(userId == null || userId.equals(""))
                throw new IFT287Exception("Vous devez entrer un nom d'utilisateur.");
            
            if(motDePasse == null || motDePasse.equals(""))
                throw new IFT287Exception("Vous devez entrer un mot de passe.");
            
            if(bd == null || bd.equals(""))
                throw new IFT287Exception("Vous devez entrer un nom de base de donn�e.");

            if (serveur == null || serveur.equals(""))
            {
                throw new IFT287Exception("Vous devez choisir un serveur.");
            }
            
            try
            {
                // Valider que les informations entr�es sont les bonnes
                Connexion cx = new Connexion(serveur, bd, userId, motDePasse);
                cx.fermer();
                
                // Sauvegarder les informations de connexion dans le contexte pour les r�utiliser
                // pour chaque client connect�                    
                getServletContext().setAttribute("serveur", serveur);
                getServletContext().setAttribute("bd", bd);
                getServletContext().setAttribute("user", userId);
                getServletContext().setAttribute("pass", motDePasse);
                

                AubergeInnHelper.creerGestionnaire(getServletContext(), request.getSession());
                
                // Afficher le menu de connexion principal de l'application 
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Accueil");
                dispatcher.forward(request, response);
            }
            catch(Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add("Erreur de connexion au serveur de base de donn�e");
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                // pour d�boggage seulement : afficher tout le contenu de
                // l'exception
                e.printStackTrace();
            }
            
        }
        catch (IFT287Exception e)
        {
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add(e.getMessage());
            request.setAttribute("listeMessageErreur", listeMessageErreur);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
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
        System.out.println("Servlet Accueil : GET");
        if (AubergeInnHelper.infoBDValide(getServletContext()))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	AubergeInnHelper.DispatchToBDConnect(request, response);
        }
    }

} // class