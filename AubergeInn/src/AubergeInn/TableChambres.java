package AubergeInn;

import java.util.List;

import javax.persistence.TypedQuery;

public class TableChambres {
	private TypedQuery<TupleChambre> stmtExiste;
	private TypedQuery<TupleChambre> stmtGetAll;
	private Connexion cx;
	
	public TableChambres(Connexion cx)
	{
		this.cx = cx;
		
        stmtExiste = cx.getConnection()
                .createQuery("select c from TupleChambre c where c.iDChambre = :idChambre", TupleChambre.class);
        stmtGetAll = cx.getConnection()
                .createQuery("select c from TupleChambre c", TupleChambre.class);
        
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleChambre getChambre(int IDChambre)
    {
    	stmtExiste.setParameter("idChambre", IDChambre);
        List<TupleChambre> chambres = stmtExiste.getResultList();
        if (!chambres.isEmpty())
            return chambres.get(0);
        else
            return null;
    }
    
    public List<TupleChambre> getChambres()
    {
        return stmtGetAll.getResultList();
    }
    
    public boolean existe(int IDChambre)
    {
    	stmtExiste.setParameter("idChambre", IDChambre);
        return !stmtExiste.getResultList().isEmpty();
    }
	
	public TupleChambre insert(TupleChambre chambre)
	{
		cx.getConnection().persist(chambre);
		return chambre;
    }
	
	public boolean delete(TupleChambre chambre)
    {
		if(chambre != null)
        {
            cx.getConnection().remove(chambre);
            return true;
        }
        return false;
    }
}
