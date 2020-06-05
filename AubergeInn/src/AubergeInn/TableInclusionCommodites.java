package AubergeInn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableInclusionCommodites {
	private PreparedStatement stmtExiste;
	private PreparedStatement stmtFromChambre;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtDeleteOnChambre;
	private Connexion cx;
	
	public TableInclusionCommodites(Connexion cx)
			throws SQLException
	{
		this.cx = cx;
        stmtExiste = cx.getConnection()
                .prepareStatement("select IDChambre, IDCommodite from InclusionCommodite where IDChambre = ? and IDCommodite = ?");
    	stmtFromChambre = cx.getConnection()
                .prepareStatement("select IDChambre, IDCommodite from InclusionCommodite where IDChambre = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into InclusionCommodite (IDChambre, IDCommodite) " + "values (?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from InclusionCommodite where IDChambre = ? and IDCommodite = ?");
        stmtDeleteOnChambre = cx.getConnection().prepareStatement("delete from InclusionCommodite where IDChambre = ?");
        
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public boolean existe(int iDChambre, int iDCommodite) throws SQLException
    {
        stmtExiste.setInt(1, iDChambre);
        stmtExiste.setInt(2, iDCommodite);
        ResultSet rset = stmtExiste.executeQuery();
        boolean inclusionCommoditeExiste = rset.next();
        rset.close();
        return inclusionCommoditeExiste;
    }
    
    public List<TupleInclusionCommodite> getInclusionCommoditeFromChambre(int IDChambre) throws SQLException
    {
    	stmtFromChambre.setInt(1, IDChambre);
        ResultSet rset = stmtFromChambre.executeQuery();
        
        List<TupleInclusionCommodite> lRes = new ArrayList<TupleInclusionCommodite>();
        while (rset.next()) {
        	TupleInclusionCommodite tupleInclusionCommodite = new TupleInclusionCommodite();
        	tupleInclusionCommodite.setIDChambre(rset.getInt(1));
        	tupleInclusionCommodite.setIDCommodite(rset.getInt(2));
        	lRes.add(tupleInclusionCommodite);
        }
        
    	rset.close();
        return lRes;
    }
	
	public void insert(int iDChambre, int iDCommodite) throws SQLException
    {
        stmtInsert.setInt(1, iDChambre);
        stmtInsert.setInt(2, iDCommodite);
        stmtInsert.executeUpdate();
    }
	
	public void delete(int iDChambre, int iDCommodite) throws SQLException
    {
		stmtDelete.setInt(1, iDChambre);
		stmtDelete.setInt(2, iDCommodite);
		stmtDelete.executeUpdate();
    }
	
	public void deleteOnChambre(int iDChambre) throws SQLException
    {
		stmtDeleteOnChambre.setInt(1, iDChambre);
		stmtDeleteOnChambre.executeUpdate();
    }
}
