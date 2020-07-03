package AubergeInn;
import java.util.ArrayList;
import java.util.List;

public class GestionInterrogation {
	private Connexion cx;
	private TableReservations tableReservations;
	private TableClients tableClients;
	private TableChambres tableChambres;
	private TableInclusionCommodites tableInclusionCommodites;
	private TableCommodites tableCommodites;
	
	public GestionInterrogation(TableReservations tableReservations, TableClients tableClients,  TableChambres tableChambres, 
			TableInclusionCommodites tableInclusionCommodites, TableCommodites tableCommodites)
	{
		this.cx = tableReservations.getConnexion();
		this.tableReservations = tableReservations;
		this.tableClients = tableClients;
		this.tableChambres = tableChambres;
		this.tableInclusionCommodites = tableInclusionCommodites;
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
        			tableReservations.getReservationsFromChambre(tupleChambre.getIDChambre())) {
        			if (tupleReservation.getDateDebut().getTime() <= System.currentTimeMillis() 
        					&& tupleReservation.getDateFin().getTime() >= System.currentTimeMillis()) {
        				estLibre = false;
        			}
        		}
        		if (estLibre) {
        			for (TupleInclusionCommodite tupleInclusionCommodite 
        					: tableInclusionCommodites.getInclusionCommoditeFromChambre(tupleChambre.getIDChambre())) {
        				tupleChambre.getCommodites().add(tableCommodites.getCommodite(tupleInclusionCommodite.getIDCommodite()));
        			}
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
        	tupleClient.setReservations(tableReservations.getReservationsFromClient(tupleClient.getIDClient()));
     	
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
        	for (TupleInclusionCommodite tupleInclusionCommodite 
        			: tableInclusionCommodites.getInclusionCommoditeFromChambre(iDChambre)) {
        		tupleChambre.getCommodites().add(tableCommodites.getCommodite(tupleInclusionCommodite.getIDCommodite()));
        	}

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
