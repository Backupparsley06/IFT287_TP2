package AubergeInn;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class TableReservations {
	private PreparedStatement stmtFromClient;
	private PreparedStatement stmtFromChambre;
	private PreparedStatement stmtInsert;
	private Connexion cx;
	
	public TableReservations(Connexion cx)
			throws SQLException
	{
		this.cx = cx;
		stmtFromClient = cx.getConnection()
                .prepareStatement("select IDReservation, IDClient, IDChambre, DateDebut, DateFin, Prix from Reservation where IDClient = ?");
		stmtFromChambre = cx.getConnection()
                .prepareStatement("select IDReservation, IDClient, IDChambre, DateDebut, DateFin, Prix from Reservation where IDChambre = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Reservation (IDClient, IDChambre, DateDebut, DateFin, Prix) " + "values (?,?,?,?,?)");
        
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public List<TupleReservation> getReservationsFromClient(int IDClient) throws SQLException
    {
    	stmtFromClient.setInt(1, IDClient);
        ResultSet rset = stmtFromClient.executeQuery();
        
        List<TupleReservation> lRes = new ArrayList<TupleReservation>();
        while (rset.next()) {
        	TupleReservation tupleReservation = new TupleReservation();
        	tupleReservation.setIDReservation(rset.getInt(1));
        	tupleReservation.setIDClient(rset.getInt(2));
        	tupleReservation.setIDChambre(rset.getInt(3));
        	tupleReservation.setDateDebut(rset.getDate(4));
        	tupleReservation.setDateFin(rset.getDate(5));
        	tupleReservation.setPrix(rset.getDouble(6));
        	lRes.add(tupleReservation);
        }
        
    	rset.close();
        return lRes;
    }
    
    public List<TupleReservation> getReservationsFromChambre(int IDChambre) throws SQLException
    {
    	stmtFromChambre.setInt(1, IDChambre);
        ResultSet rset = stmtFromChambre.executeQuery();
        
        List<TupleReservation> lRes = new ArrayList<TupleReservation>();
        while (rset.next()) {
        	TupleReservation tupleReservation = new TupleReservation();
        	tupleReservation.setIDReservation(rset.getInt(1));
        	tupleReservation.setIDClient(rset.getInt(2));
        	tupleReservation.setIDChambre(rset.getInt(3));
        	tupleReservation.setDateDebut(rset.getDate(4));
        	tupleReservation.setDateFin(rset.getDate(5));
        	tupleReservation.setPrix(rset.getDouble(6));
        	lRes.add(tupleReservation);
        }
        
    	rset.close();
        return lRes;
    }
    
	
	public void insert(int iDClient, int iDChambre, Date dateDebut, Date dateFin, double prix) throws SQLException
	{
        stmtInsert.setInt(1, iDClient);
        stmtInsert.setInt(2, iDChambre);
        stmtInsert.setDate(3, dateDebut);
        stmtInsert.setDate(4, dateFin);
        stmtInsert.setDouble(5, prix);
        stmtInsert.executeUpdate();
    }
}
