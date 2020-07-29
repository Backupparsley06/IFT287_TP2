package AubergeInnServlet;

import AubergeInn.GestionAubergeInn;

import java.sql.SQLException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class AubergeInnSessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent se)
    {
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("Session d�truite pour l'utilisateur " + se.getSession().getAttribute("userID"));
        
        GestionAubergeInn aubergeInterrogation = AubergeInnHelper.getAubergeInnInterro(se.getSession());
        if (aubergeInterrogation != null)
        {
            try
            {
                System.out.println("Fermeture de la connexion d'interrogation...");
                aubergeInterrogation.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Aucun gestionnaire d'interrogation n'avait encore �t� cr��.");
        }
        
        GestionAubergeInn aubergeUpdate = AubergeInnHelper.getAubergeInnUpdate(se.getSession());
        if (aubergeUpdate != null)
        {
            try
            {
                System.out.println("Fermeture de la connexion de mise � jour...");
                aubergeUpdate.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Aucun gestionnaire de mise � jour n'avait encore �t� cr��.");
        }
    }
}

