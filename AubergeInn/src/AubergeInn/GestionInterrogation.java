package AubergeInn;
import java.util.ArrayList;
import java.util.List;

public class GestionInterrogation {
	private Connexion cx;
	private TableReservations tableReservations;
	private TableClients tableClients;
	private TableChambres tableChambres;
	private TableCommodites tableCommodites;
	
	public GestionInterrogation(TableReservations tableReservations, TableClients tableClients,  TableChambres tableChambres, 
			TableCommodites tableCommodites)
	{
		this.cx = tableReservations.getConnexion();
		this.tableReservations = tableReservations;
		this.tableClients = tableClients;
		this.tableChambres = tableChambres;
		this.tableCommodites = tableCommodites;
	}
	
	public List<TupleChambre> afficherChambresLibres()
            throws IFT287Exception
    {
        try
        {
        	cx.demarreTransaction();
        	List<TupleChambre> chambres = new ArrayList<TupleChambre>();
        	for(TupleChambre tupleChambre : tableChambres.getChambres()) {
        		boolean estLibre = true;
        		for(TupleReservation tupleReservation: 
        			tableReservations.getReservationsFromChambre(tupleChambre)) {
        			if (tupleReservation.getDateDebut().getTime() <= System.currentTimeMillis() 
        					&& tupleReservation.getDateFin().getTime() >= System.currentTimeMillis()) {
        				estLibre = false;
        			}
        		}
        		if (estLibre) {
        			chambres.add(tupleChambre);
        		}
        		
        	}
        	
            // Commit
            cx.commit();
        	return chambres;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public TupleClient afficherClient(int iDClient)
            throws IFT287Exception
    {
		
        try
        {
        	cx.demarreTransaction();
        	if (!tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe pas: " + iDClient);
        	TupleClient tupleClient = tableClients.getClient(iDClient);
        	tupleClient.setReservations(tableReservations.getReservationsFromClient(tupleClient));
     	
            // Commit
            cx.commit();
            return tupleClient;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public TupleChambre afficherChambre(int iDChambre)
            throws IFT287Exception
    {
		
        try
        {
        	cx.demarreTransaction();
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	TupleChambre tupleChambre = tableChambres.getChambre(iDChambre);

            // Commit
            cx.commit();
            return tupleChambre;
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
}
