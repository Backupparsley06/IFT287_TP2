package AubergeInn;

import java.sql.SQLException;
import java.util.List;

public class GestionChambre {
	private Connexion cx;
	private TableChambres tableChambres;
	private TableReservations tableReservations;
	
	public GestionChambre(TableChambres tableChambres, TableReservations tableReservations)
	{
		this.cx = tableChambres.getConnexion();
		this.tableChambres = tableChambres;
		this.tableReservations = tableReservations;
	}
	
	public void ajouter(int iDChambre, String nom, String typeLit, double prixBase)
            throws IFT287Exception
    {
        try
        {
        	cx.demarreTransaction();
        	if (tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe deja: " + iDChambre);
        	if (prixBase < 0)
        		throw new IFT287Exception("Prix de base invalide");
        	
            // Ajout du Client.
        	tableChambres.insert(new TupleChambre(iDChambre, nom, typeLit, prixBase));
            
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public void supprimer(int iDChambre)
            throws IFT287Exception, SQLException
    {
        try
        {
        	cx.demarreTransaction();
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	List<TupleReservation> ltupleReservation = tableReservations.getReservationsFromChambre(tableChambres.getChambre(iDChambre));
        	for (TupleReservation tupleReservation : ltupleReservation) {
    			if ((tupleReservation.getDateDebut().getTime() <= System.currentTimeMillis() 
    					&& tupleReservation.getDateFin().getTime() >= System.currentTimeMillis())
    					|| tupleReservation.getDateDebut().getTime() >= System.currentTimeMillis()) {
    				throw new IFT287Exception("Chambre a une reservation en cours ou dans le future");
    			}
        	}
        	for (TupleReservation tupleReservation : ltupleReservation) {
        		tableReservations.delete(tupleReservation);
        	}

            // suppression de la Chambre.
        	tableChambres.delete(tableChambres.getChambre(iDChambre));
            
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
