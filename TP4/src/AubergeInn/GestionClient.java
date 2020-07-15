package AubergeInn;

import java.sql.SQLException;
import java.util.List;

public class GestionClient {
	
	private TableClients tableClients;
	private TableReservations tableReservations;
	
	
	public GestionClient(TableClients tableClients, TableReservations tableReservations)
	{
		this.tableClients = tableClients;
		this.tableReservations = tableReservations;
	}
	
	public void ajouter(int iDClient, String nom, String prenom, int age)
            throws IFT287Exception, SQLException
    {
        try
        {
        	if (tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe deja: " + iDClient);

            // Ajout du Client.
        	tableClients.insert(iDClient, nom, prenom, age);
            

        }
        catch (Exception e)
        {
            throw e;
        }
    }
	
	public void supprimer(int iDClient)
            throws IFT287Exception
    {
        try
        {
        	if (!tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe pas: " + iDClient);
        	List<TupleReservation> ltupleReservation = tableReservations.getReservationsFromClient(iDClient);
        	for (TupleReservation tupleReservation : ltupleReservation) {
    			if (tupleReservation.getDateDebut().getTime() <= System.currentTimeMillis() 
    					&& tupleReservation.getDateFin().getTime() >= System.currentTimeMillis()) {
    				throw new IFT287Exception("Client a une reservation en cours");
    			}
        	}
        	tableReservations.deleteOnClient(iDClient);

            // suppression du Client.
        	tableClients.delete(iDClient);

        }
        catch (Exception e)
        {

            throw e;
        }
    }
}
