package AubergeInn;

import java.sql.SQLException;

public class GestionClient {
	
	private Connexion cx;
	private TableClients tableClients;
	
	public GestionClient(TableClients tableClients)
	{
		this.cx = tableClients.getConnexion();
		this.tableClients = tableClients;
	}
	
	public void Ajouter(int iDClient, String nom, String prenom, int age)
            throws IFT287Exception, SQLException
    {
        try
        {
        	if (tableClients.existe(iDClient))
                throw new IFT287Exception("Membre existe déjà: " + iDClient);

            // Ajout du Client.
        	tableClients.Insert(iDClient, nom, prenom, age);
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
