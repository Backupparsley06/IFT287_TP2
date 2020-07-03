package AubergeInn;

import java.util.List;

import javax.persistence.TypedQuery;

public class TableInclusionCommodites {
	private TypedQuery<TupleInclusionCommodite> stmtExiste;
	private TypedQuery<TupleInclusionCommodite> stmtFromChambre;
	private TypedQuery<TupleInclusionCommodite> stmtDeleteOnChambre;
	private Connexion cx;
	
	public TableInclusionCommodites(Connexion cx)
	{
		this.cx = cx;
		
        stmtExiste = cx.getConnection()
                .createQuery("select i from TupleInclusionCommodite i where i.iDChambre = :iDChambre and i.iDCommodite = :iDCommodite", TupleInclusionCommodite.class);
    	stmtFromChambre = cx.getConnection()
                .createQuery("select i from TupleInclusionCommodite i where i.iDChambre = :iDChambre", TupleInclusionCommodite.class);
    	stmtDeleteOnChambre = cx.getConnection()
        		.createQuery("delete from TupleInclusionCommodite i where i.iDChambre = :iDChambre", TupleInclusionCommodite.class);
        
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public boolean existe(int iDChambre, int iDCommodite)
    {
    	stmtExiste.setParameter("iDChambre", iDChambre);
    	stmtExiste.setParameter("iDCommodite", iDCommodite);
        return !stmtExiste.getResultList().isEmpty();
    }
    
    public List<TupleInclusionCommodite> getInclusionCommoditeFromChambre(int IDChambre)
    {
    	stmtFromChambre.setParameter("iDChambre", IDChambre);
        return stmtFromChambre.getResultList();

    }
	
	public TupleInclusionCommodite insert(TupleInclusionCommodite inclusionCommodite)
    {
		cx.getConnection().persist(inclusionCommodite);
		return inclusionCommodite;
    }
	
	public boolean delete(TupleInclusionCommodite inclusionCommodite)
    {
		if(inclusionCommodite != null)
        {
            cx.getConnection().remove(inclusionCommodite);
            return true;
        }
        return false;
    }
	
	public void deleteOnChambre(int iDChambre)
    {
		stmtDeleteOnChambre.setParameter("iDChambre", iDChambre);
		stmtDeleteOnChambre.executeUpdate();
    }
}
