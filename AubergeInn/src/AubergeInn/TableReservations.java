package AubergeInn;

import static com.mongodb.client.model.Filters.eq;

import java.sql.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.util.ArrayList;

public class TableReservations {
	private Connexion cx;
	private MongoCollection<Document> reservationsCollection;
	
	public TableReservations(Connexion cx)
	{
		this.cx = cx;
		reservationsCollection = cx.getDatabase().getCollection("Reservations");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public List<TupleReservation> getReservationsFromClient(int idClient)
    {
        List<TupleReservation> lRes = new ArrayList<TupleReservation>();
        MongoCursor<Document> reservations = reservationsCollection.find(eq("idClient", idClient)).iterator();
        try
        {
            while (reservations.hasNext())
            {
            	lRes.add(new TupleReservation(reservations.next()));
            }
        }
        finally
        {
        	reservations.close();
        }
        return lRes;
    }
    
    public List<TupleReservation> getReservationsFromChambre(int idChambre)
    {
    	List<TupleReservation> lRes = new ArrayList<TupleReservation>();
        MongoCursor<Document> reservations = reservationsCollection.find(eq("idChambre", idChambre)).iterator();
        try
        {
            while (reservations.hasNext())
            {
            	lRes.add(new TupleReservation(reservations.next()));
            }
        }
        finally
        {
        	reservations.close();
        }
        return lRes;
    }
    
	
	public void insert(int idClient, int idChambre, Date dateDebut, Date dateFin, double prix)
	{
		reservationsCollection.insertOne((new TupleReservation(idClient, idChambre, dateDebut, dateFin, prix)).toDocument());
    }
	
	public boolean deleteOnChambre(int idChambre)
    {
		return reservationsCollection.deleteMany(eq("idChambre", idChambre)).getDeletedCount() > 0;
    }
	
	public boolean deleteOnClient(int idClient)
    {
		return reservationsCollection.deleteMany(eq("idClient", idClient)).getDeletedCount() > 0;
    }
	
}
