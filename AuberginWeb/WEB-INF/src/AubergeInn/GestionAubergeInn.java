package AubergeInn;
import java.sql.*;

public class GestionAubergeInn {
	private Connexion cx;
	private TableClients tableClients;
	private TableChambres tableChambres;
	private TableCommodites tableCommodites;
	private TableInclusionCommodites tableInclusionCommodite;
	private TableReservations tableReservations;
	private GestionClient gestionClient;
	private GestionChambre gestionChambre;
	private GestionCommodite gestionCommodite;
	private GestionReservation gestionReservation;
	private GestionInterrogation gestionInterrogation;
	
	public GestionAubergeInn(String serveur, String bd, String user, String password)
		throws IFT287Exception, SQLException
	{
		cx = new Connexion(serveur, bd, user, password);
		tableClients = new TableClients(cx);
		tableChambres = new TableChambres(cx);
		tableCommodites = new TableCommodites(cx);
		tableInclusionCommodite = new TableInclusionCommodites(cx);
		tableReservations = new TableReservations(cx);
		setGestionClient(new GestionClient(tableClients, tableReservations));
		setGestionChambre(new GestionChambre(tableChambres, tableReservations, tableInclusionCommodite));
		setGestionCommodite(new GestionCommodite(tableCommodites, tableChambres, tableInclusionCommodite));
		setGestionReservation(new GestionReservation(tableReservations, tableClients, tableChambres, tableInclusionCommodite, tableCommodites));
		setGestionInterrogation(new GestionInterrogation(tableReservations, tableClients, tableChambres, tableInclusionCommodite, tableCommodites));
	}
	
	public Connexion getConnexion() {
		return cx;
	}
	
    public void fermer() throws SQLException
    {
        // Fermeture de la connexion
        cx.fermer();
    }
    
    private void setGestionClient(GestionClient gestionClient)
    {
        this.gestionClient = gestionClient;
    }
    
    public GestionClient getGestionClient()
    {
        return gestionClient;
    }
    
    private void setGestionChambre(GestionChambre gestionChambre)
    {
        this.gestionChambre = gestionChambre;
    }
    
    public GestionChambre getGestionChambre()
    {
        return gestionChambre;
    }
    
    private void setGestionCommodite(GestionCommodite gestionCommodite)
    {
        this.gestionCommodite = gestionCommodite;
    }
    
    public GestionCommodite getGestionCommodite()
    {
        return gestionCommodite;
    }
    
    private void setGestionReservation(GestionReservation gestionReservation)
    {
        this.gestionReservation = gestionReservation;
    }
    
    public GestionReservation getGestionReservation()
    {
        return gestionReservation;
    }
    
    private void setGestionInterrogation(GestionInterrogation gestionInterrogation)
    {
        this.gestionInterrogation = gestionInterrogation;
    }
    
    public GestionInterrogation getGestionInterrogation()
    {
        return gestionInterrogation;
    }
}
