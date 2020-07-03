package AubergeInn;

import java.util.List;

import javax.persistence.TypedQuery;

public class TableCommodites {
	private TypedQuery<TupleCommodite> stmtExiste;
	private Connexion cx;
	
	public TableCommodites(Connexion cx)
	{
		this.cx = cx;
		
        stmtExiste = cx.getConnection()
                .createQuery("select c from TupleCommodite c where c.iDCommodite = :iDCommodite", TupleCommodite.class);
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleCommodite getCommodite(int idCommodite)
    {
    	stmtExiste.setParameter("iDCommodite", idCommodite);
        List<TupleCommodite> commodites = stmtExiste.getResultList();
        if (!commodites.isEmpty())
            return commodites.get(0);
        else
            return null;

    }
    
    public boolean existe(int idCommodite)
    {
    	stmtExiste.setParameter("iDCommodite", idCommodite);
        return !stmtExiste.getResultList().isEmpty();
    }
	
	public TupleCommodite Insert(TupleCommodite commodite)
    {
		cx.getConnection().persist(commodite);
		return commodite;
    }

}
