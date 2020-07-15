package AubergeInn;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

public class TableInclusionCommodites {
	private Connexion cx;
	private MongoCollection<Document> inclusionCommoditesCollection;
	
	public TableInclusionCommodites(Connexion cx)
	{
		this.cx = cx;
		inclusionCommoditesCollection = cx.getDatabase().getCollection("InclusionCommodites");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public boolean existe(int idChambre, int idCommodite)
    {
    	return inclusionCommoditesCollection.find(and(eq("idChambre", idChambre), eq("idCommodite", idCommodite))).first() != null;
    }
    
    public List<TupleInclusionCommodite> getInclusionCommoditeFromChambre(int idChambre)
    {
        List<TupleInclusionCommodite> liste = new ArrayList<TupleInclusionCommodite>();
        MongoCursor<Document> inclusionCommodites = inclusionCommoditesCollection.find(eq("idChambre", idChambre)).iterator();
        try
        {
            while (inclusionCommodites.hasNext())
            {
                liste.add(new TupleInclusionCommodite(inclusionCommodites.next()));
            }
        }
        finally
        {
        	inclusionCommodites.close();
        }
        
        return liste;
    }
	
	public void insert(int idChambre, int idCommodite)
    {
		inclusionCommoditesCollection.insertOne((new TupleInclusionCommodite(idChambre, idCommodite)).toDocument());
    }
	
	public boolean delete(int idChambre, int idCommodite)
    {
		return inclusionCommoditesCollection.deleteOne(and(eq("idChambre", idChambre), eq("idCommodite", idCommodite))).getDeletedCount() > 0;
    }
	
	public boolean deleteOnChambre(int iDChambre)
    {
		return inclusionCommoditesCollection.deleteMany(eq("idChambre", iDChambre)).getDeletedCount() > 0;
    }
}
