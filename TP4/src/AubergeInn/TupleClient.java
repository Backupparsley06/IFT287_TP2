package AubergeInn;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class TupleClient {
	private int idClient;
	private String nom;
	private String prenom;
	private int age;
	private List<TupleReservation> reservations;
	
	public TupleClient() {
		setReservations();
	}
	
	public TupleClient(Document d) {
		this.setIDClient(d.getInteger("idClient"));
		this.setNom(d.getString("nom"));
		this.setPrenom(d.getString("prenom"));
		this.setAge(d.getInteger("age"));
		setReservations();
	}
	
	public TupleClient(int iDClient, String nom, String prenom, int age) {
		this.setIDClient(iDClient);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAge(age);
		setReservations();
	}
	
	public int getIDClient()
    {
        return idClient;
    }

    public void setIDClient(int iDClient)
    {
        this.idClient = iDClient;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
    
    public List<TupleReservation> getReservations()
    {
        return reservations;
    }
    
    public void setReservations(List<TupleReservation> reservations)
    {
        this.reservations = reservations;
    }

    public void setReservations()
    {
        this.reservations = new ArrayList<TupleReservation>();
    }
    
    public double getPrixTotal()
    {
    	double prix = 0;
    	for (TupleReservation r : getReservations()) 
    		prix += r.getPrix();
    	return prix;
    }
    
    public Document toDocument()
    {
    	return new Document().append("idClient", idClient)
    			             .append("nom", nom)
    			             .append("prenom", prenom)
    			             .append("age", age);
    }
}
