package AubergeInn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class TupleChambre {
    @Id @GeneratedValue
    private long id;
	
	private int iDChambre;
	private String nom;
	private String typeLit;
	private double prixBase;
	@Transient
	private List<TupleCommodite> commodites;
	
	public TupleChambre() {
		this.setCommodites();
	}
	
	public TupleChambre(int iDChambre, String nom, String typeLit, double prixBase) {
		this.setIDChambre(iDChambre);
		this.setNom(nom);
		this.setTypeLit(typeLit);
		this.setPrixBase(prixBase);
		this.setCommodites();
	}
	
	public int getIDChambre()
    {
        return iDChambre;
    }

    public void setIDChambre(int iDChambre)
    {
        this.iDChambre = iDChambre;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getTypeLit()
    {
        return typeLit;
    }

    public void setTypeLit(String typeLit)
    {
        this.typeLit = typeLit;
    }

    public double getPrixBase()
    {
        return prixBase;
    }

    public void setPrixBase(double prixBase)
    {
        this.prixBase = prixBase;
    }
    
	public List<TupleCommodite> getCommodites()
    {
        return commodites;
    }

    public void setCommodites()
    {
        this.commodites = new ArrayList<TupleCommodite>();
    }
    
    public double getPrixTotal()
    {
    	double prix = getPrixBase();
    	for (TupleCommodite c : getCommodites()) 
    		prix += c.getSurplusPrix();
    	return prix;
    	
    }

}
