package AubergeInn;

import java.util.List;

public class GestionClient {
	
	private Connexion cx;
	private TableClients tableClients;
	private TableReservations tableReservations;
	
	
	public GestionClient(TableClients tableClients, TableReservations tableReservations)
	{
		this.cx = tableClients.getConnexion();
		this.tableClients = tableClients;
		this.tableReservations = tableReservations;
	}
	
	public void ajouter(int iDClient, String nom, String prenom, int age)
            throws IFT287Exception
    {
        try
        {
        	cx.demarreTransaction();
        	
        	if (tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe deja: " + iDClient);

            // Ajout du Client.
        	tableClients.insert(new TupleClient(iDClient, nom, prenom, age));
            
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
            throws IFT287Exception
    {
        try
        {
        	cx.demarreTransaction();
        	if (!tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe pas: " + iDClient);
        	List<TupleReservation> ltupleReservation = tableReservations.getReservationsFromClient(tableClients.getClient(iDClient));
        	for (TupleReservation tupleReservation : ltupleReservation) {
    			if (tupleReservation.getDateDebut().getTime() <= System.currentTimeMillis() 
    					&& tupleReservation.getDateFin().getTime() >= System.currentTimeMillis()) {
    				throw new IFT287Exception("Client a une reservation en cours ");
    			}
        	}
        	for (TupleReservation tupleReservation : ltupleReservation) {
        		tableReservations.delete(tupleReservation);
        	}
            // suppression du Client.
        	tableClients.delete(tableClients.getClient(iDClient));
            
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
