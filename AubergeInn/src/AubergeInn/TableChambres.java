package AubergeInn;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

public class TableChambres {
	private Connexion cx;
	private MongoCollection<Document> chambresCollection;
	
	public TableChambres(Connexion cx)
	{
		this.cx = cx;
		chambresCollection = cx.getDatabase().getCollection("Chambres");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleChambre getChambre(int IDChambre)
    {
    	Document c = chambresCollection.find(eq("idChambre", IDChambre)).first();
    	if(c != null)
    	{
    		return new TupleChambre(c);
    	}
        return null;
    }
    
    public List<TupleChambre> getChambres()
    {
        List<TupleChambre> liste = new ArrayList<TupleChambre>();
        MongoCursor<Document> chambres = chambresCollection.find().iterator();
        try
        {
            while (chambres.hasNext())
            {
                liste.add(new TupleChambre(chambres.next()));
            }
        }
        finally
        {
        	chambres.close();
        }
        
        return liste;
    }
    
    public boolean existe(int IDChambre)
    {
    	return chambresCollection.find(eq("idChambre", IDChambre)).first() != null;
    }
	
	public void insert(int iDChambre, String nom, String typeLit, double prixBase)
	{
		chambresCollection.insertOne((new TupleChambre(iDChambre, nom, typeLit, prixBase)).toDocument());
    }
	
	public boolean delete(int iDChambre)
    {
		return chambresCollection.deleteOne(eq("idChambre", iDChambre)).getDeletedCount() > 0;
    }
}
