package AubergeInn;


import org.bson.Document;

import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;

public class TableClients {
	private Connexion cx;
	private MongoCollection<Document> clientsCollection;
	
	public TableClients(Connexion cx)
	{
		this.cx = cx;
		clientsCollection = cx.getDatabase().getCollection("Clients");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleClient getClient(int iDClient)
    {
    	Document c = clientsCollection.find(eq("idClient", iDClient)).first();
    	if(c != null)
    	{
    		return new TupleClient(c);
    	}
        return null;
    }
    
    public boolean existe(int iDClient)
    {
        return clientsCollection.find(eq("idClient", iDClient)).first() != null;
    }
	
	public void insert(int iDClient, String nom, String prenom, int age)
    {
		clientsCollection.insertOne((new TupleClient(iDClient, nom, prenom, age)).toDocument());

    }
	
	public boolean delete(int iDClient)
    {
		return clientsCollection.deleteOne(eq("idClient", iDClient)).getDeletedCount() > 0;
    }
	
}
