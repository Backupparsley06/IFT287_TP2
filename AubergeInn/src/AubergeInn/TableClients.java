package AubergeInn;

import java.util.List;

import javax.persistence.*;



public class TableClients {

	private Connexion cx;
    private TypedQuery<TupleClient> stmtExiste;

	
	public TableClients(Connexion cx)
	{
		this.cx = cx;
		stmtExiste = cx.getConnection().createQuery("select c from TupleClient c where c.iDClient = :idClient", TupleClient.class);
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleClient getClient(int iDClient)
    {
    	stmtExiste.setParameter("idClient", iDClient);
        List<TupleClient> clients = stmtExiste.getResultList();
        if (!clients.isEmpty())
            return clients.get(0);
        else
            return null;
    }
    
    public boolean existe(int iDClient)
    {
    	stmtExiste.setParameter("idClient", iDClient);
        return !stmtExiste.getResultList().isEmpty();
    }
	
	public TupleClient insert(TupleClient client)
    {
		cx.getConnection().persist(client);
		return client;
    }
	
	public boolean delete(TupleClient client)
    {
		if(client != null)
        {
            cx.getConnection().remove(client);
            return true;
        }
        return false;
    }
	
	

}
