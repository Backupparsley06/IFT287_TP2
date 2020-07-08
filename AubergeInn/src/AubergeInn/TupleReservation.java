package AubergeInn;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TupleReservation {
    @Id @GeneratedValue
    private long id;
	
    private TupleClient client;
    private TupleChambre chambre;
	private Date dateDebut;
	private Date dateFin;
	private double prix;
	
	public TupleReservation() {
	}
	
	public TupleReservation(TupleClient client, TupleChambre chambre, Date dateDebut, Date dateFin, double prix) {
		this.setClient(client);
		this.setChambre(chambre);
		this.setDateDebut(dateDebut);
		this.setDateFin(dateFin);
		this.setPrix(prix);
	}

	
	public TupleClient getClient()
    {
        return client;
    }

    public void setClient(TupleClient client)
    {
        this.client = client;
    }

    public TupleChambre getChambre()
    {
        return chambre;
    }

    public void setChambre(TupleChambre chambre)
    {
        this.chambre = chambre;
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
