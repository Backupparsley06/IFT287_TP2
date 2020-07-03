package AubergeInn;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleReservation {
    @Id @GeneratedValue
    private long id;
	
    @GeneratedValue
	private int iDReservation;
	private int iDClient;
	private int iDChambre;
	private Date dateDebut;
	private Date dateFin;
	private double prix;
	
	public TupleReservation() {
	}
	
	public TupleReservation(int iDClient, int iDChambre, Date dateDebut, Date dateFin, double prix) {
		this.setIDClient(iDClient);
		this.setIDChambre(iDChambre);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setPrix(prix);
	}
	
	public TupleReservation(int iDReservation, int iDClient, int iDChambre, Date dateDebut, Date dateFin, double prix) {
		this.setIDReservation(iDReservation);
		this.setIDClient(iDClient);
		this.setIDChambre(iDChambre);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setPrix(prix);
	}
	
	public int getIDReservation()
    {
        return iDReservation;
    }

    public void setIDReservation(int iDReservation)
    {
        this.iDReservation = iDReservation;
    }
	
	public int getIDClient()
    {
        return iDClient;
    }

    public void setIDClient(int iDClient)
    {
        this.iDClient = iDClient;
    }

    public int getIDChambre()
    {
        return iDChambre;
    }

    public void setIDChambre(int iDChambre)
    {
        this.iDChambre = iDChambre;
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
}
