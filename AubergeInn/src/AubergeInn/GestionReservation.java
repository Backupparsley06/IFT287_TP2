package AubergeInn;

import java.sql.Date;
import java.sql.SQLException;

public class GestionReservation {
	private TableReservations tableReservations;
	private TableClients tableClients;
	private TableChambres tableChambres;
	private TableInclusionCommodites tableInclusionCommodites;
	private TableCommodites tableCommodites;
	
	public GestionReservation(TableReservations tableReservations, TableClients tableClients,  TableChambres tableChambres, 
			TableInclusionCommodites tableInclusionCommodites, TableCommodites tableCommodites)
	{
		this.tableReservations = tableReservations;
		this.tableClients = tableClients;
		this.tableChambres = tableChambres;
		this.tableInclusionCommodites = tableInclusionCommodites;
		this.tableCommodites = tableCommodites;
	}
	
	public void reserver(int iDClient, int iDChambre, Date dateDebut, Date dateFin)
            throws IFT287Exception, SQLException
    {
        try
        {
        	if (!tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe pas : " + iDClient);
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas : " + iDChambre);
        	if(dateDebut.getTime() > dateFin.getTime()) {
        		throw new IFT287Exception("Dates invalides");
        	}
        	
        	for (TupleReservation tupleReservation : tableReservations.getReservationsFromChambre(iDChambre)) {
        		if (!(dateFin.getTime() < tupleReservation.getDateDebut().getTime() 
        				|| dateDebut.getTime() > tupleReservation.getDateFin().getTime()))
        		{
        			throw new IFT287Exception("Il existe déjà une reservation a cette date");
        		}
        	}
        	
        	double prix = tableChambres.getChambre(iDChambre).getPrixBase();
        	for (TupleInclusionCommodite tupleInclusionCommodite 
        			: tableInclusionCommodites.getInclusionCommoditeFromChambre(iDChambre))
        	{
        		prix += tableCommodites.getCommodite(tupleInclusionCommodite.getIDCommodite()).getSurplusPrix();
        	}

        	tableReservations.insert(iDClient, iDChambre, dateDebut, dateFin, prix);

        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
