package AubergeInn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableClients {
	private PreparedStatement stmtExiste;
	private PreparedStatement stmtAll;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtDelete;
	private Connexion cx;
	
	public TableClients(Connexion cx)
			throws SQLException
	{
		this.cx = cx;
        stmtExiste = cx.getConnection()
                .prepareStatement("select IDClient, Nom, Prenom, Age from Client where IDClient = ?");
        stmtAll = cx.getConnection()
                .prepareStatement("select IDClient, Nom, Prenom, Age from Client");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Client (IDClient, nom, Prenom, Age) " + "values (?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from Client where IDClient = ?");
        
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public TupleClient getClient(int iDClient) throws SQLException
    {
    	stmtExiste.setInt(1, iDClient);
        ResultSet rset = stmtExiste.executeQuery();
        if (rset.next())
        {
        	TupleClient tupleClient = new TupleClient();
        	tupleClient.setIDClient(rset.getInt(1));
        	tupleClient.setNom(rset.getString(2));
        	tupleClient.setPrenom(rset.getString(3));
        	tupleClient.setAge(rset.getInt(4));
            rset.close();
            return tupleClient;
        }
        else
        	rset.close();
            return null;
    }
    
    public List<TupleClient> getClients() throws SQLException
    {
        ResultSet rset = stmtAll.executeQuery();
        List<TupleClient> clients = new ArrayList<TupleClient>();
        while (rset.next())
        {
        	TupleClient tupleClient = new TupleClient();
        	tupleClient.setIDClient(rset.getInt(1));
        	tupleClient.setNom(rset.getString(2));
        	tupleClient.setPrenom(rset.getString(3));
        	tupleClient.setAge(rset.getInt(4));
            clients.add(tupleClient);
        }
        rset.close();
        return clients;
    }
    
    public boolean existe(int iDClient) throws SQLException
    {
        stmtExiste.setInt(1, iDClient);
        ResultSet rset = stmtExiste.executeQuery();
        boolean clientExiste = rset.next();
        rset.close();
        return clientExiste;
    }
	
	public void insert(int iDClient, String nom, String prenom, int age) throws SQLException
    {
        stmtInsert.setInt(1, iDClient);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, prenom);
        stmtInsert.setInt(4, age);
        stmtInsert.executeUpdate();
    }
	
	public void delete(int iDClient) throws SQLException
    {
		stmtDelete.setInt(1, iDClient);
		stmtDelete.executeUpdate();
    }
	
	

}
