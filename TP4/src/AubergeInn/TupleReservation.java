package AubergeInn;

import java.sql.Date;

import org.bson.Document;

public class TupleReservation {
	private int idClient;
	private int idChambre;
	private Date dateDebut;
	private Date dateFin;
	private double prix;
	
	public TupleReservation() {
	}
	
	public TupleReservation(Document d) {
		this.setIDClient(d.getInteger("idClient"));
		this.setIDChambre(d.getInteger("idChambre"));
		this.setDateDebut(new Date(d.getDate("dateDebut").getTime()));
		this.setDateFin(new Date(d.getDate("dateFin").getTime()));
		this.setPrix(d.getDouble("prix"));
	}
	
	public TupleReservation(int iDClient, int iDChambre, Date dateDebut, Date dateFin, double prix) {
		this.setIDClient(iDClient);
		this.setIDChambre(iDChambre);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setPrix(prix);
	}
	
	public int getIDClient()
    {
        return idClient;
    }

    public void setIDClient(int iDClient)
    {
        this.idClient = iDClient;
    }

    public int getIDChambre()
    {
        return idChambre;
    }

    public void setIDChambre(int iDChambre)
    {
        this.idChambre = iDChambre;
    }

    public Date getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin()
    {
        return dateFin;
    }

    public void setDateFin(Date dateFin)
    {
        this.dateFin = dateFin;
    }
    
	public double getPrix()
    {
        return prix;
    }

    public void setPrix(double prix)
    {
        this.prix = prix;
    }
    
    public Document toDocument()
    {
    	return new Document().append("idClient", idClient)
    			             .append("idChambre", idChambre)
    			             .append("dateDebut", new java.util.Date(dateDebut.getTime()))
    			             .append("dateFin", new java.util.Date(dateFin.getTime()))
    			             .append("prix", prix);
    }
}
