package AubergeInn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableChambres {
	private PreparedStatement stmtExiste;
	private PreparedStatement stmtGetAll;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	public TableChambres(Connexion cx)
			throws SQLException
	{
		this.cx = cx;
        stmtExiste = cx.getConnection()
                .prepareStatement("select IDChambre, Nom, TypeLit, PrixBase from Chambre where IDChambre = ?");
        stmtGetAll = cx.getConnection()
                .prepareStatement("select IDChambre, Nom, TypeLit, PrixBase from Chambre");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Chambre (IDChambre, Nom, TypeLit, PrixBase) " + "values (?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from Chambre where IDChambre = ?");
        
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleChambre getChambre(int IDChambre) throws SQLException
    {
    	stmtExiste.setInt(1, IDChambre);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next())
        {
        	TupleChambre tupleChambre = new TupleChambre();
        	tupleChambre.setIDChambre(rset.getInt(1));
        	tupleChambre.setNom(rset.getString(2));
        	tupleChambre.setTypeLit(rset.getString(3));
        	tupleChambre.setPrixBase(rset.getDouble(4));
            rset.close();
            return tupleChambre;
        }
        else
        	rset.close();
            return null;
    }
    
    public List<TupleChambre> getChambres() throws SQLException
    {
        ResultSet rset = stmtGetAll.executeQuery();           
        List<TupleChambre> lCha = new ArrayList<TupleChambre>();
        while (rset.next()) {
        	TupleChambre tupleChambre = new TupleChambre();
        	tupleChambre.setIDChambre(rset.getInt(1));
        	tupleChambre.setNom(rset.getString(2));
        	tupleChambre.setTypeLit(rset.getString(3));
        	tupleChambre.setPrixBase(rset.getDouble(4));
        	lCha.add(tupleChambre);
        }
        
    	rset.close();
        return lCha;
    }
    
    public boolean existe(int IDChambre) throws SQLException
    {
        stmtExiste.setInt(1, IDChambre);
        ResultSet rset = stmtExiste.executeQuery();
        boolean chambreExiste = rset.next();
        rset.close();
        return chambreExiste;
    }
	
	public void insert(int iDChambre, String nom, String typeLit, double prixBase) throws SQLException
	{
        stmtInsert.setInt(1, iDChambre);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, typeLit);
        stmtInsert.setDouble(4, prixBase);
        stmtInsert.executeUpdate();
    }
	
	public void delete(int iDChambre) throws SQLException
    {
		stmtDelete.setInt(1, iDChambre);
		stmtDelete.executeUpdate();
    }
}
