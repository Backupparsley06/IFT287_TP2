package AubergeInn;
import java.sql.*;

public class GestionAubergeInn {
	private Connexion cx;
	private TableClients tableClients;
	private GestionClient gestionClient;
	
	public GestionAubergeInn(String serveur, String bd, String user, String password)
		throws IFT287Exception, SQLException
	{
		cx = new Connexion(serveur, bd, user, password);
		tableClients = new TableClients(cx);
		setGestionClient(new GestionClient(tableClients));
		
		
	}
	
    public void fermer() throws SQLException
    {
        // Fermeture de la connexion
        cx.fermer();
    }
    
    private void setGestionClient(GestionClient gestionClient)
    {
        this.gestionClient = gestionClient;
    }
    
    public GestionClient GetGestionClient()
    {
        return gestionClient;
    }
}
