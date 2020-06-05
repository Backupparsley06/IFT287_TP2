package AubergeInn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableCommodites {
	private PreparedStatement stmtExiste;
	private PreparedStatement stmtInsert;
	private Connexion cx;
	
	public TableCommodites(Connexion cx)
			throws SQLException
	{
		this.cx = cx;
        stmtExiste = cx.getConnection()
                .prepareStatement("select IDCommodite, Description, SurplusPrix from Commodite where IDCommodite = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Commodite (IDCommodite, Description, SurplusPrix) " + "values (?,?,?)");
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleCommodite getCommodite(int idCommodite) throws SQLException
    {
    	stmtExiste.setInt(1, idCommodite);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next())
        {
        	TupleCommodite tupleCommodite = new TupleCommodite();
        	tupleCommodite.setIDCommodite(rset.getInt(1));
        	tupleCommodite.setDescription(rset.getString(2));
        	tupleCommodite.setSurplusPrix(rset.getDouble(3));
            rset.close();
            return tupleCommodite;
        }
        else
        	rset.close();
            return null;
    }
    
    public boolean existe(int IDCommodite) throws SQLException
    {
        stmtExiste.setInt(1, IDCommodite);
        ResultSet rset = stmtExiste.executeQuery();
        boolean commoditeExiste = rset.next();
        rset.close();
        return commoditeExiste;
    }
	
	public void Insert(int iDCommodite, String description, double surplusPrix) throws SQLException
    {
        stmtInsert.setInt(1, iDCommodite);
        stmtInsert.setString(2, description);
        stmtInsert.setDouble(3, surplusPrix);
        stmtInsert.executeUpdate();
    }

}
