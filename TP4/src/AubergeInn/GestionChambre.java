package AubergeInn;

import java.util.List;

public class GestionChambre {
	private TableChambres tableChambres;
	private TableReservations tableReservations;
	private TableInclusionCommodites tableInclusionCommodite;
	
	public GestionChambre(TableChambres tableChambres, TableReservations tableReservations, TableInclusionCommodites tableInclusionCommodite)
	{
		this.tableChambres = tableChambres;
		this.tableReservations = tableReservations;
		this.tableInclusionCommodite = tableInclusionCommodite;
	}
	
	public void ajouter(int iDChambre, String nom, String typeLit, double prixBase)
            throws IFT287Exception
    {
        try
        {
        	if (tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe deja : " + iDChambre);
        	if (prixBase < 0)
        		throw new IFT287Exception("Prix de base invalide");
        	
        	
        	
            // Ajout du Client.
        	tableChambres.insert(iDChambre, nom, typeLit, prixBase);
            
        }
        catch (Exception e)
        {

            throw e;
        }
    }
	
	public void supprimer(int iDChambre)
            throws IFT287Exception
    {
        try
        {
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	List<TupleReservation> ltupleReservation = tableReservations.getReservationsFromChambre(iDChambre);
        	for (TupleReservation tupleReservation : ltupleReservation) {
    			if ((tupleReservation.getDateDebut().getTime() <= System.currentTimeMillis() 
    					&& tupleReservation.getDateFin().getTime() >= System.currentTimeMillis())
    					|| tupleReservation.getDateDebut().getTime() >= System.currentTimeMillis()) {
    				throw new IFT287Exception("Chambre a une reservation en cours ou dans le future");
    			}
        	}
        	
        	tableReservations.deleteOnChambre(iDChambre);
        	
        	// suppression des commoditees inclus
        	tableInclusionCommodite.deleteOnChambre(iDChambre);

            // suppression de la Chambre.
        	tableChambres.delete(iDChambre);
            
        }
        catch (Exception e)
        {

            throw e;
        }
    }
}
