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
	
	public void ajouter(int iDClient, String nom, String prenom, int age)
            throws IFT287Exception, SQLException
    {
        try
        {
        	if (tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe déjà : " + iDClient);

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
	
	public void supprimer(int iDClient)
            throws IFT287Exception, SQLException
    {
        try
        {
        	if (!tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe pas : " + iDClient);

            // suppression du Client.
        	tableClients.Delete(iDClient);
            
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
