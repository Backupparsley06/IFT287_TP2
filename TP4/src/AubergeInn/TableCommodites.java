package AubergeInn;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class TableCommodites {
	private Connexion cx;
	private MongoCollection<Document> commoditesCollection;
	
	public TableCommodites(Connexion cx)
	{
		this.cx = cx;
		commoditesCollection = cx.getDatabase().getCollection("Commodites");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleCommodite getCommodite(int idCommodite) 
    {
    	Document c = commoditesCollection.find(eq("idCommodite", idCommodite)).first();
    	if(c != null)
    	{
    		return new TupleCommodite(c);
    	}
        return null;
    }
    
    public boolean existe(int idCommodite)
    {
    	return commoditesCollection.find(eq("idCommodite", idCommodite)).first() != null;
    }
	
	public void Insert(int iDCommodite, String description, double surplusPrix)
    {
		commoditesCollection.insertOne((new TupleCommodite(iDCommodite, description, surplusPrix)).toDocument());
    }

}
