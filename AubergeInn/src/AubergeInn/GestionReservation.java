package AubergeInn;

import java.sql.Date;

public class GestionReservation {
	private Connexion cx;
	private TableReservations tableReservations;
	private TableClients tableClients;
	private TableChambres tableChambres;
	private TableCommodites tableCommodites;
	
	public GestionReservation(TableReservations tableReservations, TableClients tableClients,  TableChambres tableChambres, 
			TableCommodites tableCommodites)
	{
		this.cx = tableReservations.getConnexion();
		this.tableReservations = tableReservations;
		this.tableClients = tableClients;
		this.tableChambres = tableChambres;
		this.tableCommodites = tableCommodites;
	}
	
	public void reserver(int iDClient, int iDChambre, Date dateDebut, Date dateFin)
            throws IFT287Exception
    {
        try
        {
        	cx.demarreTransaction();
        	if (!tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe pas: " + iDClient);
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas: " + iDChambre);
        	if(dateDebut.getTime() > dateFin.getTime()) {
        		throw new IFT287Exception("Dates invalides");
        	}
        	
        	for (TupleReservation tupleReservation : tableReservations.getReservationsFromChambre(tableChambres.getChambre(iDChambre))) {
        		if (!(dateFin.getTime() < tupleReservation.getDateDebut().getTime() 
        				|| dateDebut.getTime() > tupleReservation.getDateFin().getTime()))
        		{
        			throw new IFT287Exception("Il existe deja une reservation a cette date ");
        		}
        	}
        	
        	TupleChambre chambre = tableChambres.getChambre(iDChambre);
        	double prix = chambre.getPrixBase();
        	for (TupleCommodite tupleCommodite 
        			: chambre.getCommodites())
        	{
        		prix += tupleCommodite.getSurplusPrix();
        	}

        	tableReservations.insert(new TupleReservation(tableClients.getClient(iDClient), tableChambres.getChambre(iDChambre), dateDebut, dateFin, prix));
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
