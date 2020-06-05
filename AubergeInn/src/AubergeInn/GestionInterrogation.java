package AubergeInn;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;

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
	
	public void afficherChambresLibres()
            throws IFT287Exception, SQLException
    {
		DecimalFormat df = new DecimalFormat("#0.00");
        try
        {
        	System.out.println("idChambre : nom : typeLit : prixDeLocation");
        	System.out.println("------------------------------------------");
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
        			double prix = tupleChambre.getPrixBase();
        			for (TupleInclusionCommodite tupleInclusionCommodite 
        					: tableInclusionCommodites.getInclusionCommoditeFromChambre(tupleChambre.getIDChambre())) {
        				prix += tableCommodites.getCommodite(tupleInclusionCommodite.getIDCommodite()).getSurplusPrix();
        			}
        			System.out.println(tupleChambre.getIDChambre() 
        					+ " : " + tupleChambre.getNom()
        					+ " : " + tupleChambre.getTypeLit()
        					+ " : " + df.format(prix));
        		}
        		
        	}
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public void afficherClient(int iDClient)
            throws IFT287Exception, SQLException
    {
		DecimalFormat df = new DecimalFormat("#0.00");
        try
        {
        	if (!tableClients.existe(iDClient))
                throw new IFT287Exception("Client existe pas : " + iDClient);
        	System.out.println("idClient : nom : prenom : age");
        	System.out.println("-----------------------------");
        	TupleClient tupleClient = tableClients.getClient(iDClient);
        	System.out.println(tupleClient.getIDClient() 
        			+ " : " + tupleClient.getNom()
        			+ " : " + tupleClient.getPrenom()
        			+ " : " + tupleClient.getAge());
        	
        	double prixTotal = 0;
        	System.out.println("idReservation : idClient : idChambre : dateDebut : dateFin : prixDeLocation");
        	System.out.println("---------------------------------------------------------------------------");
        	for(TupleReservation tupleReservation: 
    			tableReservations.getReservationsFromClient(tupleClient.getIDClient())) {
        		System.out.println(tupleReservation.getIDReservation() 
            			+ " : " + tupleReservation.getIDClient()
            			+ " : " + tupleReservation.getIDChambre()
            			+ " : " + tupleReservation.getDateDebut()
            			+ " : " + tupleReservation.getDateFin()
            			+ " : " + df.format(tupleReservation.getPrix()));
        		prixTotal += tupleReservation.getPrix();
    		}
        	System.out.println(" Total ");
        	System.out.println("-------");
        	System.out.println(df.format(prixTotal));
        	
            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }
	
	public void afficherChambre(int iDChambre)
            throws IFT287Exception, SQLException
    {
		DecimalFormat df = new DecimalFormat("#0.00");
        try
        {
        	if (!tableChambres.existe(iDChambre))
                throw new IFT287Exception("Chambre existe pas : " + iDChambre);
        	
        	System.out.println("idChambre : nom : typeLit : prixDeBase");
        	System.out.println("--------------------------------------");
        	TupleChambre tupleChambre = tableChambres.getChambre(iDChambre);
        	System.out.println(tupleChambre.getIDChambre() 
					+ " : " + tupleChambre.getNom()
					+ " : " + tupleChambre.getTypeLit()
					+ " : " + df.format(tupleChambre.getPrixBase()));
        	double prixTotal = tupleChambre.getPrixBase();
        	System.out.println("idCommodite : description : surPlusPrix");
        	System.out.println("---------------------------------------");
        	for (TupleInclusionCommodite tupleInclusionCommodite 
        			: tableInclusionCommodites.getInclusionCommoditeFromChambre(iDChambre)) {
        		TupleCommodite tupleCommodite = tableCommodites.getCommodite(tupleInclusionCommodite.getIDCommodite());
        		prixTotal += tupleCommodite.getSurplusPrix();
        		System.out.println(tupleCommodite.getIDCommodite() 
    					+ " : " + tupleCommodite.getDescription()
    					+ " : " + df.format(tupleCommodite.getSurplusPrix()));
        	}
        	System.out.println(" Total ");
        	System.out.println("-------");
        	System.out.println(df.format(prixTotal));
        	
        	
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
