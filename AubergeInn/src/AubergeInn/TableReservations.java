package AubergeInn;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

public class TableReservations {
	private TypedQuery<TupleReservation> stmtFromClient;
	private TypedQuery<TupleReservation> stmtFromChambre;
	private Connexion cx;
	
	public TableReservations(Connexion cx)
			throws SQLException
	{
		this.cx = cx;
		
		stmtFromClient = cx.getConnection().createQuery("select r from TupleReservation r where r.client = :iDClient", TupleReservation.class);
		stmtFromChambre = cx.getConnection().createQuery("select r from TupleReservation r where r.chambre = :idChambre", TupleReservation.class);
	}
	
    public Connexion getConnexion()
    {
        return cx;
    }
    
    public List<TupleReservation> getReservationsFromClient(TupleClient IDClient)
    {
    	stmtFromClient.setParameter("iDClient", IDClient);
        return stmtFromClient.getResultList();
    }
    
    public List<TupleReservation> getReservationsFromChambre(TupleChambre IDChambre)
    {
        stmtFromChambre.setParameter("idChambre", IDChambre);
        return stmtFromChambre.getResultList();
    }
    
	
	public TupleReservation insert(TupleReservation reservation)
	{
        cx.getConnection().persist(reservation);
		return reservation;
    }
	
	public boolean delete(TupleReservation reservation)
    {
		if(reservation != null)
        {
            cx.getConnection().remove(reservation);
            return true;
        }
        return false;
    }
}
